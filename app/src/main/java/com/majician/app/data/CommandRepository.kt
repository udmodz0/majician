package com.majician.app.data

data class CommandOption(
    val label: String,
    val value: String
)

data class MajicCommand(
    val id: String,
    val title: String,
    val description: String,
    val category: CommandCategory,
    val commandTemplate: String,
    val hasParameter: Boolean = true,
    val parameterHint: String = "Value",
    val defaultValue: String = "",
    val options: List<CommandOption>? = null,
    val isCountryPicker: Boolean = false,
    val isCityLocationPicker: Boolean = false
)

enum class CommandCategory(val displayName: String) {
    DEVICE("Device Identity"),
    BATTERY("Battery"),
    ANIMATIONS("Animations"),
    DISPLAY("Display & DPI"),
    LOCATION_CARRIER("Location & MCC"),
    SYSTEM("System Tweaks")
}

object CommandRepository {
    val commands = listOf(
        // Device Identity Tweaks (100% working via settings)
        MajicCommand(
            id = "spoof_device_name",
            title = "Set Global Device Name",
            description = "Change public device broadcast name.",
            category = CommandCategory.DEVICE,
            commandTemplate = "settings put global device_name %s",
            parameterHint = "Device Name (e.g. Pixel 9 Pro)",
            defaultValue = "Pixel 9 Pro"
        ),
        MajicCommand(
            id = "spoof_bt_name",
            title = "Set Bluetooth Device Name",
            description = "Change Bluetooth discoverable name.",
            category = CommandCategory.DEVICE,
            commandTemplate = "settings put secure bluetooth_name %s",
            parameterHint = "Bluetooth Name",
            defaultValue = "Pixel 9 Pro"
        ),
        MajicCommand(
            id = "spoof_hostname",
            title = "Set Network Hostname",
            description = "Change network broadcast hostname.",
            category = CommandCategory.DEVICE,
            commandTemplate = "settings put global net_hostname %s",
            parameterHint = "Hostname",
            defaultValue = "pixel9pro"
        ),

        // Battery
        MajicCommand(
            id = "bat_level",
            title = "Set Battery Level",
            description = "Spoof battery percentage (0-100).",
            category = CommandCategory.BATTERY,
            commandTemplate = "dumpsys battery set level %s",
            parameterHint = "Level (0-100)",
            defaultValue = "85"
        ),
        MajicCommand(
            id = "bat_plugged",
            title = "Set Power Source",
            description = "Select power charging connection state.",
            category = CommandCategory.BATTERY,
            commandTemplate = "dumpsys battery set plugged %s",
            options = listOf(
                CommandOption("🔌 AC Charger", "1"),
                CommandOption("💻 USB Port", "2"),
                CommandOption("⚡ Wireless Charger", "4"),
                CommandOption("🔋 Unplugged", "0")
            )
        ),
        MajicCommand(
            id = "bat_reset",
            title = "Reset Battery Status",
            description = "Restore actual hardware battery status.",
            category = CommandCategory.BATTERY,
            commandTemplate = "dumpsys battery reset",
            hasParameter = false
        ),

        // Animations
        MajicCommand(
            id = "anim_window",
            title = "Window Animation Scale",
            description = "Speed up or slow down window transitions.",
            category = CommandCategory.ANIMATIONS,
            commandTemplate = "settings put global window_animation_scale %s",
            options = listOf(
                CommandOption("⚡ Animation Off (0.0x)", "0.0"),
                CommandOption("🚀 Fast (0.5x)", "0.5"),
                CommandOption("▶ Normal (1.0x)", "1.0"),
                CommandOption("🐢 Slow (1.5x)", "1.5"),
                CommandOption("🐌 Very Slow (2.0x)", "2.0")
            )
        ),
        MajicCommand(
            id = "anim_transition",
            title = "Transition Animation Scale",
            description = "Activity transition speed.",
            category = CommandCategory.ANIMATIONS,
            commandTemplate = "settings put global transition_animation_scale %s",
            options = listOf(
                CommandOption("⚡ Animation Off (0.0x)", "0.0"),
                CommandOption("🚀 Fast (0.5x)", "0.5"),
                CommandOption("▶ Normal (1.0x)", "1.0"),
                CommandOption("🐢 Slow (1.5x)", "1.5")
            )
        ),
        MajicCommand(
            id = "anim_duration",
            title = "Animator Duration Scale",
            description = "Global animator duration.",
            category = CommandCategory.ANIMATIONS,
            commandTemplate = "settings put global animator_duration_scale %s",
            options = listOf(
                CommandOption("⚡ Animation Off (0.0x)", "0.0"),
                CommandOption("🚀 Fast (0.5x)", "0.5"),
                CommandOption("▶ Normal (1.0x)", "1.0"),
                CommandOption("🐢 Slow (1.5x)", "1.5")
            )
        ),

        // Display
        MajicCommand(
            id = "disp_density",
            title = "Screen Density (DPI)",
            description = "Change screen DPI for compactness.",
            category = CommandCategory.DISPLAY,
            commandTemplate = "wm density %s",
            parameterHint = "DPI (e.g. 400)",
            defaultValue = "420"
        ),
        MajicCommand(
            id = "disp_density_reset",
            title = "Reset Screen Density",
            description = "Restore default screen DPI.",
            category = CommandCategory.DISPLAY,
            commandTemplate = "wm density reset",
            hasParameter = false
        ),
        MajicCommand(
            id = "disp_size",
            title = "Screen Resolution",
            description = "Change screen width x height.",
            category = CommandCategory.DISPLAY,
            commandTemplate = "wm size %s",
            parameterHint = "WidthxHeight (e.g. 1080x2340)",
            defaultValue = "1080x2340"
        ),
        MajicCommand(
            id = "disp_size_reset",
            title = "Reset Resolution",
            description = "Restore factory resolution.",
            category = CommandCategory.DISPLAY,
            commandTemplate = "wm size reset",
            hasParameter = false
        ),
        MajicCommand(
            id = "disp_font_scale",
            title = "System Font Scale",
            description = "Adjust global text sizing.",
            category = CommandCategory.DISPLAY,
            commandTemplate = "settings put system font_scale %s",
            options = listOf(
                CommandOption("🔍 Small (0.85)", "0.85"),
                CommandOption("▶ Normal (1.00)", "1.00"),
                CommandOption("🔎 Large (1.15)", "1.15"),
                CommandOption("🧐 Extra Large (1.30)", "1.30")
            )
        ),

        // Location & Carrier
        MajicCommand(
            id = "carrier_mcc_picker",
            title = "Select Country Carrier (MCC/MNC)",
            description = "Choose from Asian, African, European, American & Global carriers.",
            category = CommandCategory.LOCATION_CARRIER,
            commandTemplate = "settings put global gsm_sim_operator_numeric %s",
            isCountryPicker = true
        ),
        MajicCommand(
            id = "city_location_picker",
            title = "Select Preset City Location",
            description = "Select a global city location preset.",
            category = CommandCategory.LOCATION_CARRIER,
            commandTemplate = "settings put secure mock_location 1",
            isCityLocationPicker = true
        ),
        MajicCommand(
            id = "loc_mock",
            title = "Mock Location Toggle",
            description = "Enable or disable mock location allowance.",
            category = CommandCategory.LOCATION_CARRIER,
            commandTemplate = "settings put secure mock_location %s",
            options = listOf(
                CommandOption("✅ Enable Mock Location", "1"),
                CommandOption("❌ Disable Mock Location", "0")
            )
        ),

        // System Tweaks
        MajicCommand(
            id = "sys_dark_mode",
            title = "System Dark Mode",
            description = "Toggle system-wide dark / light mode.",
            category = CommandCategory.SYSTEM,
            commandTemplate = "cmd uimode night %s",
            options = listOf(
                CommandOption("🌙 Dark Mode (Yes)", "yes"),
                CommandOption("☀️ Light Mode (No)", "no")
            )
        ),
        MajicCommand(
            id = "sys_immersive",
            title = "Immersive Full Screen Mode",
            description = "Hide status bar and navigation bar.",
            category = CommandCategory.SYSTEM,
            commandTemplate = "settings put global policy_control %s",
            options = listOf(
                CommandOption("📺 Enable Immersive Full Screen", "immersive.full=*"),
                CommandOption("📱 Normal System Bars", "null")
            )
        ),
        MajicCommand(
            id = "sys_doze_idle",
            title = "Force Doze Idle Mode",
            description = "Force device into deep power-saving Doze mode.",
            category = CommandCategory.SYSTEM,
            commandTemplate = "dumpsys deviceidle force-idle",
            hasParameter = false
        ),
        MajicCommand(
            id = "sys_touches",
            title = "Show Touches",
            description = "Show visual touch indicator circles.",
            category = CommandCategory.SYSTEM,
            commandTemplate = "settings put system show_touches %s",
            options = listOf(
                CommandOption("👁️ Show Touches", "1"),
                CommandOption("🚫 Hide Touches", "0")
            )
        ),
        MajicCommand(
            id = "sys_stay_awake",
            title = "Stay Awake While Plugged In",
            description = "Prevent screen sleep while charging.",
            category = CommandCategory.SYSTEM,
            commandTemplate = "settings put global stay_on_while_plugged_in %s",
            options = listOf(
                CommandOption("☀️ Keep Awake on Charger", "3"),
                CommandOption("🌙 Normal Sleep", "0")
            )
        )
    )
}
