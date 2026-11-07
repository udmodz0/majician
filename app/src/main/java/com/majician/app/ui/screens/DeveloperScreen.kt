package com.majician.app.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majician.app.R
import com.majician.app.ui.components.GlassButton
import com.majician.app.ui.components.GlassCard
import com.majician.app.ui.theme.*

@Composable
fun DeveloperScreen() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    fun openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun shareApp() {
        try {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Explore Android Tweaks, Device Spoofing & System Controls on Majician app by UDMODZ!\nJoin Telegram: https://t.me/udmodz0"
                )
            }
            context.startActivity(Intent.createChooser(intent, "Share Majician App"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Developer Avatar Ring with UDMODZ Logo
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.linearGradient(
                            listOf(PureBlue, NeonCyan)
                        )
                    )
                    .padding(3.dp)
                    .clip(CircleShape)
                    .background(DarkBackground),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.udmodz),
                    contentDescription = "UDMODZ Logo",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "UDMODZ",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Full Stack Developer & Software Architect",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = NeonCyan
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Developer Mission Glass Card
            GlassCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = NeonCyan,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Developer Mission",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = TextPrimary
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "I am a developer passionate about building tools that empower users. From privacy-focused messaging mods to enterprise-grade code obfuscation, my goal is to push the boundaries of software.",
                    color = TextSecondary,
                    fontSize = 13.sp,
                    lineHeight = 19.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Experience", color = TextSecondary, fontSize = 11.sp)
                        Text(text = "5+ Years", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                    Column {
                        Text(text = "Location", color = TextSecondary, fontSize = 11.sp)
                        Text(text = "Global / Remote", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Connect & Follow Section Header
            Text(
                text = "Connect & Follow",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = TextPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Social Buttons Grid
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    DeveloperSocialButton(
                        modifier = Modifier.weight(1f),
                        title = "Telegram",
                        icon = Icons.AutoMirrored.Filled.Send,
                        accentColor = Color(0xFF0088CC),
                        onClick = { openUrl("https://t.me/udmodz0") }
                    )
                    DeveloperSocialButton(
                        modifier = Modifier.weight(1f),
                        title = "WhatsApp",
                        icon = Icons.Default.Phone,
                        accentColor = Color(0xFF25D366),
                        onClick = { openUrl("https://whatsapp.com/channel/0029Vb5uLwF7z4kgGnpfGU3D") }
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    DeveloperSocialButton(
                        modifier = Modifier.weight(1f),
                        title = "YouTube",
                        icon = Icons.Default.PlayArrow,
                        accentColor = Color(0xFFFF0000),
                        onClick = { openUrl("https://youtube.com/@udmodz") }
                    )
                    DeveloperSocialButton(
                        modifier = Modifier.weight(1f),
                        title = "GitHub",
                        icon = Icons.Default.Build,
                        accentColor = Color(0xFF9D4EDD),
                        onClick = { openUrl("https://github.com/udmodz0") }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Share App Button
            GlassButton(
                text = "Share Majician App",
                onClick = { shareApp() },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(160.dp)) // Sufficient padding for floating bottom nav bar
        }
    }
}

@Composable
fun DeveloperSocialButton(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    accentColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = 0.08f))
            .border(1.dp, accentColor.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = accentColor,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                color = TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
