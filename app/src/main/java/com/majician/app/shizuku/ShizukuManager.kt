package com.majician.app.shizuku

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import rikka.shizuku.Shizuku
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.Executors

object ShizukuManager {

    private var appContext: Context? = null

    private val _isAppInstalled = MutableStateFlow(false)
    val isAppInstalled: StateFlow<Boolean> = _isAppInstalled.asStateFlow()

    private val _isServiceRunning = MutableStateFlow(false)
    val isServiceRunning: StateFlow<Boolean> = _isServiceRunning.asStateFlow()

    private val _isAuthorized = MutableStateFlow(false)
    val isAuthorized: StateFlow<Boolean> = _isAuthorized.asStateFlow()

    private val executor = Executors.newSingleThreadExecutor()

    private val binderReceivedListener = Shizuku.OnBinderReceivedListener {
        checkStatus()
    }

    private val binderDeadListener = Shizuku.OnBinderDeadListener {
        checkStatus()
    }

    private val permissionListener = Shizuku.OnRequestPermissionResultListener { requestCode, grantResult ->
        if (requestCode == 1001) {
            _isAuthorized.value = grantResult == PackageManager.PERMISSION_GRANTED
        }
    }

    fun init(context: Context) {
        appContext = context.applicationContext
        try {
            Shizuku.addBinderReceivedListenerSticky(binderReceivedListener)
            Shizuku.addBinderDeadListener(binderDeadListener)
            Shizuku.addRequestPermissionResultListener(permissionListener)
            checkStatus()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun checkStatus() {
        try {
            val ctx = appContext
            if (ctx != null) {
                val installed = try {
                    ctx.packageManager.getPackageInfo("moe.shizuku.manager", 0)
                    true
                } catch (e: Exception) {
                    false
                }
                _isAppInstalled.value = installed
            }

            val serviceRunning = try {
                Shizuku.pingBinder()
            } catch (e: Throwable) {
                false
            }
            _isServiceRunning.value = serviceRunning

            if (serviceRunning) {
                try {
                    val authorized = Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
                    _isAuthorized.value = authorized
                } catch (e: Throwable) {
                    _isAuthorized.value = false
                }
            } else {
                _isAuthorized.value = false
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun requestPermission() {
        try {
            val ctx = appContext
            checkStatus()
            if (!Shizuku.pingBinder()) {
                if (ctx != null) {
                    Toast.makeText(ctx, "Shizuku service is not running on device. Please start Shizuku first.", Toast.LENGTH_LONG).show()
                }
                return
            }

            if (Shizuku.checkSelfPermission() != PackageManager.PERMISSION_GRANTED) {
                Shizuku.requestPermission(1001)
            } else {
                _isAuthorized.value = true
                if (ctx != null) {
                    Toast.makeText(ctx, "Shizuku already authorized!", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            val ctx = appContext
            if (ctx != null) {
                Toast.makeText(ctx, "Error requesting permission: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun executeCommand(command: String, callback: (CommandResult) -> Unit) {
        executor.submit {
            try {
                if (!_isAuthorized.value && !Shizuku.pingBinder()) {
                    callback(CommandResult(false, "", "Shizuku not authorized or service not running."))
                    return@submit
                }

                val method = Shizuku::class.java.getDeclaredMethod(
                    "newProcess",
                    Array<String>::class.java,
                    Array<String>::class.java,
                    String::class.java
                )
                method.isAccessible = true
                val process = method.invoke(
                    null,
                    arrayOf("sh", "-c", command),
                    null,
                    null
                ) as Process

                val reader = BufferedReader(InputStreamReader(process.inputStream))
                val errorReader = BufferedReader(InputStreamReader(process.errorStream))

                val output = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    output.append(line).append("\n")
                }

                val error = StringBuilder()
                while (errorReader.readLine().also { line = it } != null) {
                    error.append(line).append("\n")
                }

                process.waitFor()
                val exitCode = process.exitValue()

                val outStr = output.toString().trim()
                val errStr = error.toString().trim()

                callback(
                    CommandResult(
                        success = exitCode == 0,
                        output = outStr.ifEmpty { errStr },
                        error = errStr.takeIf { exitCode != 0 } ?: ""
                    )
                )
            } catch (e: Throwable) {
                callback(CommandResult(false, "", e.message ?: "Unknown execution error"))
            }
        }
    }
}

data class CommandResult(
    val success: Boolean,
    val output: String,
    val error: String
)
