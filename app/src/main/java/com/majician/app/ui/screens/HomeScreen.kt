package com.majician.app.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majician.app.data.CommandCategory
import com.majician.app.data.CommandRepository
import com.majician.app.data.LocationRepository
import com.majician.app.data.MajicCommand
import com.majician.app.data.UpdateInfo
import com.majician.app.data.UpdateManager
import com.majician.app.shizuku.ShizukuManager
import com.majician.app.ui.components.FirstStartFollowDialog
import com.majician.app.ui.components.GlassButton
import com.majician.app.ui.components.GlassCard
import com.majician.app.ui.components.UpdateDialog
import com.majician.app.ui.theme.*

@Composable
fun HomeScreen() {
    val context = LocalContext.current

    val isAuthorized by ShizukuManager.isAuthorized.collectAsState()

    // SharedPreferences check for First-Start Follow Dialog
    val prefs = remember { context.getSharedPreferences("majician_prefs", Context.MODE_PRIVATE) }
    var showFirstStartDialog by remember { mutableStateOf(!prefs.getBoolean("has_seen_follow_dialog", false)) }

    // Remote Update State
    var availableUpdate by remember { mutableStateOf<UpdateInfo?>(null) }

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<CommandCategory?>(null) }
    var activeCommand by remember { mutableStateOf<MajicCommand?>(null) }
    var parameterInput by remember { mutableStateOf("") }
    var executionResult by remember { mutableStateOf<String?>(null) }
    var currentBottomTab by remember { mutableIntStateOf(0) } // 0: Home, 1: Spoof, 2: System, 3: Location, 4: Developer

    var showCountryPicker by remember { mutableStateOf(false) }
    var countrySearchQuery by remember { mutableStateOf("") }

    var showCityPicker by remember { mutableStateOf(false) }
    var citySearchQuery by remember { mutableStateOf("") }

    var showCustomLocationDialog by remember { mutableStateOf(false) }
    var customLat by remember { mutableStateOf("6.9271") }
    var customLng by remember { mutableStateOf("79.8612") }

    LaunchedEffect(Unit) {
        ShizukuManager.checkStatus()
        UpdateManager.checkForUpdates(context) { info ->
            availableUpdate = info
        }
    }

    // First Start Follow Dialog
    if (showFirstStartDialog) {
        FirstStartFollowDialog(
            onDismiss = { showFirstStartDialog = false }
        )
    }

    // Update Available Dialog
    availableUpdate?.let { updateInfo ->
        UpdateDialog(
            updateInfo = updateInfo,
            onDismiss = { availableUpdate = null }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 16.dp)
        ) {
            // Top Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // App Logo Icon
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.linearGradient(
                                listOf(NeonCyan, PureBlue)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = "Logo",
                        tint = DarkBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Search Bar in Header
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 10.dp)
                        .height(44.dp)
                        .clip(RoundedCornerShape(22.dp))
                        .background(Color.White.copy(alpha = 0.08f))
                        .border(1.dp, NeonCyan.copy(alpha = 0.4f), RoundedCornerShape(22.dp)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = TextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(modifier = Modifier.weight(1f)) {
                            if (searchQuery.isEmpty()) {
                                Text(text = "Search tweaks & spoofing...", color = TextSecondary, fontSize = 14.sp)
                            }
                            androidx.compose.foundation.text.BasicTextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                textStyle = TextStyle(color = TextPrimary, fontSize = 14.sp),
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                // Sparkle Icon
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Sparkle",
                        tint = NeonCyan,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                // Developer Contact Header Icon Button
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            if (currentBottomTab == 4) NeonCyan.copy(alpha = 0.25f) else Color.White.copy(alpha = 0.12f)
                        )
                        .border(
                            1.dp,
                            if (currentBottomTab == 4) NeonCyan else Color.White.copy(alpha = 0.2f),
                            CircleShape
                        )
                        .clickable {
                            currentBottomTab = 4
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Developer Contact",
                        tint = NeonCyan,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            if (currentBottomTab == 4) {
                // Developer Screen Tab Content
                DeveloperScreen()
            } else {
                // Commands List Screen View
                // Shizuku Authorization Banner if not authorized
                if (!isAuthorized) {
                    Spacer(modifier = Modifier.height(2.dp))
                    GlassCard(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { ShizukuManager.requestPermission() }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "Security",
                                    tint = NeonCyan,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "Shizuku Authorization Required",
                                        color = TextPrimary,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Tap here to request permission",
                                        color = TextSecondary,
                                        fontSize = 11.sp
                                    )
                                }
                            }
                            GlassButton(
                                text = "Grant",
                                onClick = { ShizukuManager.requestPermission() }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                }

                Spacer(modifier = Modifier.height(2.dp))

                // Category Filter Pill Chips
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    item {
                        CategoryPill(
                            text = "All",
                            isSelected = selectedCategory == null,
                            onClick = { selectedCategory = null }
                        )
                    }
                    items(CommandCategory.values()) { category ->
                        CategoryPill(
                            text = category.displayName,
                            isSelected = selectedCategory == category,
                            onClick = { selectedCategory = category }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Filtered Commands List based on search, category, and bottom navigation tab
                val displayedCommands = remember(searchQuery, selectedCategory, currentBottomTab) {
                    CommandRepository.commands.filter { cmd ->
                        val tabMatch = when (currentBottomTab) {
                            0 -> true // Home / All
                            1 -> cmd.category == CommandCategory.DEVICE || cmd.category == CommandCategory.BATTERY
                            2 -> cmd.category == CommandCategory.ANIMATIONS || cmd.category == CommandCategory.DISPLAY || cmd.category == CommandCategory.SYSTEM
                            3 -> cmd.category == CommandCategory.LOCATION_CARRIER
                            else -> true
                        }
                        val matchesCategory = selectedCategory == null || cmd.category == selectedCategory
                        val matchesSearch = searchQuery.isBlank() ||
                                cmd.title.contains(searchQuery, ignoreCase = true) ||
                                cmd.description.contains(searchQuery, ignoreCase = true)
                        tabMatch && matchesCategory && matchesSearch
                    }
                }

                LazyColumn(
                    contentPadding = PaddingValues(bottom = 100.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(displayedCommands) { command ->
                        GlassCard(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                if (!isAuthorized) {
                                    Toast.makeText(context, "Please grant Shizuku permission first!", Toast.LENGTH_SHORT).show()
                                    ShizukuManager.requestPermission()
                                    return@GlassCard
                                }
                                if (command.isCountryPicker) {
                                    countrySearchQuery = ""
                                    showCountryPicker = true
                                } else if (command.isCityLocationPicker) {
                                    citySearchQuery = ""
                                    showCityPicker = true
                                } else if (command.options != null || command.hasParameter) {
                                    parameterInput = command.defaultValue
                                    activeCommand = command
                                } else {
                                    ShizukuManager.executeCommand(command.commandTemplate) { res ->
                                        executionResult = if (res.success) {
                                            "Success: ${command.title}\n${res.output.ifEmpty { "Command executed successfully." }}"
                                        } else {
                                            "Failed: ${res.error.ifEmpty { res.output }}"
                                        }
                                    }
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = command.title,
                                        color = TextPrimary,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = command.description,
                                        color = TextSecondary,
                                        fontSize = 12.sp
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(NeonCyan.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Build,
                                        contentDescription = "Execute",
                                        tint = NeonCyan,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }

                    item {
                        executionResult?.let { result ->
                            Spacer(modifier = Modifier.height(6.dp))
                            GlassCard(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Result",
                                            color = NeonGreen,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = result,
                                            color = TextPrimary,
                                            fontSize = 13.sp
                                        )
                                    }
                                    TextButton(onClick = { executionResult = null }) {
                                        Text("Dismiss", color = NeonCyan)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Floating Bottom Navigation Bar with dark, high-contrast backdrop and vibrant cyan icons
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(horizontal = 14.dp, vertical = 10.dp)
                .fillMaxWidth()
                .height(64.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(Color(0xEE0A0A16))
                .border(1.5.dp, NeonCyan.copy(alpha = 0.5f), RoundedCornerShape(32.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavIcon(
                    icon = Icons.Default.Home,
                    label = "Home",
                    isSelected = currentBottomTab == 0
                ) {
                    currentBottomTab = 0
                    selectedCategory = null
                }
                BottomNavIcon(
                    icon = Icons.Default.Phone,
                    label = "Spoof",
                    isSelected = currentBottomTab == 1
                ) {
                    currentBottomTab = 1
                    selectedCategory = CommandCategory.DEVICE
                }
                BottomNavIcon(
                    icon = Icons.Default.Settings,
                    label = "System",
                    isSelected = currentBottomTab == 2
                ) {
                    currentBottomTab = 2
                    selectedCategory = CommandCategory.SYSTEM
                }
                BottomNavIcon(
                    icon = Icons.Default.LocationOn,
                    label = "Location",
                    isSelected = currentBottomTab == 3
                ) {
                    currentBottomTab = 3
                    selectedCategory = CommandCategory.LOCATION_CARRIER
                }
                BottomNavIcon(
                    icon = Icons.Default.Person,
                    label = "Developer",
                    isSelected = currentBottomTab == 4
                ) {
                    currentBottomTab = 4
                }
            }
        }

        // Active Command Dialog (Options List or Text Input)
        activeCommand?.let { cmd ->
            AlertDialog(
                onDismissRequest = { activeCommand = null },
                containerColor = DarkSurface,
                title = {
                    Text(text = cmd.title, color = TextPrimary, fontWeight = FontWeight.Bold)
                },
                text = {
                    Column {
                        Text(text = cmd.description, color = TextSecondary, fontSize = 13.sp)
                        Spacer(modifier = Modifier.height(12.dp))

                        if (cmd.options != null) {
                            // Selectable Options List
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                cmd.options.forEach { opt ->
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(Color.White.copy(alpha = 0.08f))
                                            .border(1.dp, NeonCyan.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                                            .clickable {
                                                val finalCmd = String.format(cmd.commandTemplate, opt.value)
                                                activeCommand = null
                                                ShizukuManager.executeCommand(finalCmd) { res ->
                                                    executionResult = if (res.success) {
                                                        "Success: ${cmd.title} (${opt.label})\n${res.output.ifEmpty { "Setting applied successfully." }}"
                                                    } else {
                                                        "Failed: ${res.error.ifEmpty { res.output }}"
                                                    }
                                                }
                                            }
                                            .padding(14.dp)
                                    ) {
                                        Text(text = opt.label, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                                    }
                                }
                            }
                        } else {
                            // Manual Text Field
                            OutlinedTextField(
                                value = parameterInput,
                                onValueChange = { parameterInput = it },
                                label = { Text(cmd.parameterHint) },
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = NeonCyan,
                                    unfocusedBorderColor = TextSecondary,
                                    focusedLabelColor = NeonCyan,
                                    unfocusedLabelColor = TextSecondary,
                                    cursorColor = NeonCyan
                                )
                            )
                        }
                    }
                },
                confirmButton = {
                    if (cmd.options == null) {
                        GlassButton(
                            text = "Execute",
                            onClick = {
                                val finalCmd = String.format(cmd.commandTemplate, parameterInput)
                                activeCommand = null
                                ShizukuManager.executeCommand(finalCmd) { res ->
                                    executionResult = if (res.success) {
                                        "Success: ${cmd.title}\n${res.output.ifEmpty { "Command executed successfully." }}"
                                    } else {
                                        "Failed: ${res.error.ifEmpty { res.output }}"
                                    }
                                }
                            }
                        )
                    }
                },
                dismissButton = {
                    TextButton(onClick = { activeCommand = null }) {
                        Text("Cancel", color = TextSecondary)
                    }
                }
            )
        }

        // Searchable Country MCC/MNC Picker Dialog
        if (showCountryPicker) {
            val filteredCountries = remember(countrySearchQuery) {
                LocationRepository.countryMccs.filter {
                    countrySearchQuery.isBlank() ||
                            it.countryName.contains(countrySearchQuery, ignoreCase = true) ||
                            it.carrierName.contains(countrySearchQuery, ignoreCase = true) ||
                            it.mccMnc.contains(countrySearchQuery, ignoreCase = true)
                }
            }

            AlertDialog(
                onDismissRequest = { showCountryPicker = false },
                containerColor = DarkSurface,
                title = {
                    Text(text = "Select Country Carrier (MCC/MNC)", color = TextPrimary, fontWeight = FontWeight.Bold)
                },
                text = {
                    Column(modifier = Modifier.fillMaxHeight(0.7f)) {
                        OutlinedTextField(
                            value = countrySearchQuery,
                            onValueChange = { countrySearchQuery = it },
                            label = { Text("Search Country or Carrier...") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonCyan,
                                unfocusedBorderColor = TextSecondary
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(filteredCountries) { country ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color.White.copy(alpha = 0.08f))
                                        .border(1.dp, NeonCyan.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                                        .clickable {
                                            showCountryPicker = false
                                            if (country.mccMnc == "CUSTOM") {
                                                parameterInput = "310260"
                                                activeCommand = CommandRepository.commands.first { it.id == "carrier_mcc_picker" }
                                            } else {
                                                val cmd = "settings put global gsm_sim_operator_numeric ${country.mccMnc}; settings put global gsm_operator_numeric ${country.mccMnc}; settings put global sim_mcc ${country.mcc}; settings put global sim_mnc ${country.mnc}"
                                                ShizukuManager.executeCommand(cmd) { res ->
                                                    executionResult = if (res.success) {
                                                        "Success: Spoofed Carrier & SIM to ${country.countryName} ${country.flag} - ${country.carrierName}\nMCC: ${country.mcc}, MNC: ${country.mnc} (${country.mccMnc})"
                                                    } else {
                                                        "Failed: ${res.error.ifEmpty { res.output }}"
                                                    }
                                                }
                                            }
                                        }
                                        .padding(12.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(text = country.flag, fontSize = 20.sp)
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Column {
                                                Text(text = country.countryName, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                                Text(text = country.carrierName, color = TextSecondary, fontSize = 12.sp)
                                            }
                                        }
                                        Text(text = if (country.mccMnc == "CUSTOM") "Custom" else country.mccMnc, color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    }
                                }
                            }
                        }
                    }
                },
                confirmButton = {},
                dismissButton = {
                    TextButton(onClick = { showCountryPicker = false }) {
                        Text("Close", color = TextSecondary)
                    }
                }
            )
        }

        // Global City Location Picker Dialog (With Search Bar)
        if (showCityPicker) {
            val filteredCities = remember(citySearchQuery) {
                LocationRepository.cityLocations.filter {
                    citySearchQuery.isBlank() ||
                            it.isCustom ||
                            it.cityName.contains(citySearchQuery, ignoreCase = true) ||
                            it.countryName.contains(citySearchQuery, ignoreCase = true)
                }
            }

            AlertDialog(
                onDismissRequest = { showCityPicker = false },
                containerColor = DarkSurface,
                title = {
                    Text(text = "Select Global City Location", color = TextPrimary, fontWeight = FontWeight.Bold)
                },
                text = {
                    Column(modifier = Modifier.fillMaxHeight(0.7f)) {
                        OutlinedTextField(
                            value = citySearchQuery,
                            onValueChange = { citySearchQuery = it },
                            label = { Text("Search City or Country...") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonCyan,
                                unfocusedBorderColor = TextSecondary
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(filteredCities) { city ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color.White.copy(alpha = 0.08f))
                                        .border(1.dp, NeonCyan.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                                        .clickable {
                                            showCityPicker = false
                                            if (city.isCustom) {
                                                showCustomLocationDialog = true
                                            } else {
                                                val cmd = "settings put secure mock_location 1"
                                                ShizukuManager.executeCommand(cmd) { res ->
                                                    executionResult = if (res.success) {
                                                        "Success: Selected ${city.cityName}, ${city.countryName}\nLat: ${city.latitude}, Lng: ${city.longitude}"
                                                    } else {
                                                        "Failed: ${res.error.ifEmpty { res.output }}"
                                                    }
                                                }
                                            }
                                        }
                                        .padding(14.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(text = city.cityName, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                            Text(text = city.countryName, color = TextSecondary, fontSize = 12.sp)
                                        }
                                        if (!city.isCustom) {
                                            Text(
                                                text = "${city.latitude}, ${city.longitude}",
                                                color = NeonCyan,
                                                fontSize = 11.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                confirmButton = {},
                dismissButton = {
                    TextButton(onClick = { showCityPicker = false }) {
                        Text("Close", color = TextSecondary)
                    }
                }
            )
        }

        // Custom Location Input Dialog (Latitude & Longitude)
        if (showCustomLocationDialog) {
            AlertDialog(
                onDismissRequest = { showCustomLocationDialog = false },
                containerColor = DarkSurface,
                title = {
                    Text(text = "Custom Location Coordinates", color = TextPrimary, fontWeight = FontWeight.Bold)
                },
                text = {
                    Column {
                        Text(text = "Enter custom latitude and longitude coordinates:", color = TextSecondary, fontSize = 13.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedTextField(
                            value = customLat,
                            onValueChange = { customLat = it },
                            label = { Text("Latitude (e.g. 6.9271)") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonCyan,
                                unfocusedBorderColor = TextSecondary
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = customLng,
                            onValueChange = { customLng = it },
                            label = { Text("Longitude (e.g. 79.8612)") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonCyan,
                                unfocusedBorderColor = TextSecondary
                            )
                        )
                    }
                },
                confirmButton = {
                    GlassButton(
                        text = "Apply Location",
                        onClick = {
                            showCustomLocationDialog = false
                            val cmd = "settings put secure mock_location 1"
                            ShizukuManager.executeCommand(cmd) { res ->
                                executionResult = if (res.success) {
                                    "Success: Set Custom Mock Location\nLatitude: $customLat, Longitude: $customLng"
                                } else {
                                    "Failed: ${res.error.ifEmpty { res.output }}"
                                }
                            }
                        }
                    )
                },
                dismissButton = {
                    TextButton(onClick = { showCustomLocationDialog = false }) {
                        Text("Cancel", color = TextSecondary)
                    }
                }
            )
        }
    }
}

@Composable
fun CategoryPill(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val bg = if (isSelected) {
        Brush.horizontalGradient(listOf(NeonCyan, PureBlue))
    } else {
        Brush.horizontalGradient(listOf(Color.White.copy(alpha = 0.08f), Color.White.copy(alpha = 0.03f)))
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(bg)
            .border(1.dp, if (isSelected) Color.Transparent else Color.White.copy(alpha = 0.15f), RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) DarkBackground else TextPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BottomNavIcon(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val activeColor = NeonCyan // Bright high contrast cyan (#00E5FF)
    val inactiveColor = Color(0xFFA0A0B8) // High contrast silver/light gray
    val color = if (isSelected) activeColor else inactiveColor
    val bg = if (isSelected) activeColor.copy(alpha = 0.18f) else Color.Transparent

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(bg)
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            color = color,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}
