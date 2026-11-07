<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:4000ff,50:7c3aed,100:00d4ff&height=220&section=header&text=Majician%20-%20UDMODZ&fontSize=52&fontColor=ffffff&animation=fadeIn&fontAlignY=35&desc=⚡%20Advanced%20Android%20System%20Tweaks%20%26%20Shizuku%20Spoofer&descAlignY=55&descSize=18" width="100%"/>

<br/>

[![Android](https://img.shields.io/badge/Platform-Android%208.0%2B-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.22-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Shizuku](https://img.shields.io/badge/Privileges-Shizuku%20v13.1.5-FF6F00?style=for-the-badge&logo=android&logoColor=white)](https://shizuku.rikka.app)

[![License](https://img.shields.io/badge/License-MIT-00d4ff?style=for-the-badge&logoColor=white)](https://github.com/udmodz0)
[![Status](https://img.shields.io/badge/Status-Active-00ff88?style=for-the-badge&logoColor=white)](https://github.com/udmodz0)
[![Developer](https://img.shields.io/badge/Developer-UDMODZ-7c3aed?style=for-the-badge&logo=github&logoColor=white)](https://github.com/udmodz0)

<br/>

```text
███╗   ███╗ █████╗      ██╗██╗ ██████╗██╗ █████╗ ███╗   ██╗
████╗ ████║██╔══██╗     ██║██║██╔════╝██║██╔══██╗████╗  ██║
██╔████╔██║███████║     ██║██║██║     ██║███████║██╔██╗ ██║
██║╚██╔╝██║██╔══██║██   ██║██║██║     ██║██╔══██║██║╚██╗██║
██║ ╚═╝ ██║██║  ██║╚█████╔╝██║╚██████╗██║██║  ██║██║ ╚████║
╚═╝     ╚═╝╚═╝  ╚═╝ ╚════╝ ╚═╝ ╚═════╝╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝
```

</div>

---

## <img src="https://media.giphy.com/media/iY8CRBdQXODJSCERIr/giphy.gif" width="30"/> &nbsp;About

> **Majician (UDMODZ Edition)** is a next-generation Android system tuning, device identity customization, and spoofing utility engineered with **Jetpack Compose (Material 3)** and powered by the **Shizuku API**. Majician gives power users, testers, and enthusiasts elevated ADB shell execution capabilities (`settings put`, `wm`, `dumpsys`, and `cmd`) directly on-device without requiring traditional root access (Magisk, KernelSU, or APatch).

> [!IMPORTANT]
> **🚀 100% Rootless via Shizuku Service**:
> Majician operates over Shizuku's privileged IPC binder framework. Simply launch the Shizuku manager via **Wireless Debugging**, **ADB terminal**, or **Root**, grant permission with a single tap, and execute modifications instantly without reboots or risk of bricking your device.

<div align="center">

| 🧩 Feature | 📝 Description |
|:---:|:---|
| 🛡️ **Rootless ADB Privileges** | Direct integration with Shizuku IPC binder (v13+) for secure elevated execution |
| 📱 **Device Identity Spoofing** | Change global broadcast device name, Bluetooth name, and network hostname |
| 🔋 **Battery State Control** | Spoof battery percentages (0–100%) and simulate AC, USB, or Wireless charging states |
| 🖥️ **Display & DPI Customization** | Dynamically adjust screen density (DPI), resolution (`wm size`), and font scale |
| ⚡ **Animation Velocity Control** | Speed up or slow down window, transition, and animator durations (0.0x–2.0x) |
| 📡 **Worldwide Carrier (MCC/MNC)** | Inject carrier numeric codes across Asia, Europe, Americas, Africa & Middle East |
| 📍 **Global Mock Location** | Instant presets for major global capitals plus custom latitude/longitude inputs |
| 🌙 **Deep System Controls** | Force system-wide Dark Mode, Immersive Full Screen, Touches, & Doze idle |
| 💎 **Cyber Glassmorphic UI** | Clean dark aesthetic with neon cyan accents, real-time search, and category chips |
| 🔄 **OTA In-App Updates** | Automated version checks via Cloudflare Workers with direct APK downloads |

</div>

<img src="https://user-images.githubusercontent.com/73097560/115834477-dbab4500-a447-11eb-908a-139a6edaec5c.gif" width="100%"/>

## 🚀 Getting Started & Installation

### 1. Prerequisites
- **Android OS**: Android 8.0 (Oreo / API 26) or higher.
- **Shizuku App**: Installed and running on your device. Download from [Shizuku Official (rikka.app)](https://shizuku.rikka.app) or [Google Play Store](https://play.google.com/store/apps/details?id=moe.shizuku.manager).

### 2. Shizuku Setup
1. Launch **Shizuku**.
2. Activate Shizuku using one of the following methods:
   - **Wireless Debugging (Android 11+)**: Pair via Developer Options and tap *Start*.
   - **ADB from PC**: Run `adb shell sh /sdcard/Android/data/moe.shizuku.manager/files/start.sh`.
   - **Root Access**: Tap *Start* if your device is rooted.

### 3. Install Majician
Clone this repository and compile the APK using Android Studio or Gradle CLI:

```bash
# Clone the repository
git clone https://github.com/udmodz0/majician.git
cd majician

# Build the Debug APK
./gradlew assembleDebug

# Or build the optimized Release APK
./gradlew assembleRelease
```

The compiled APK will be located at:
```text
app/build/outputs/apk/debug/app-debug.apk
```

Transfer and install `app-debug.apk` onto your target Android device.

### 4. Grant Permissions & Apply Tweaks
1. Launch **Majician**.
2. If prompted with the **Shizuku Authorization Required** banner, tap **Grant**.
3. Allow Shizuku permission when the dialog pops up.
4. Browse or search tweaks, select your preferred settings, and tap to execute.

---

## 🛠️ System Tweaks & Commands Matrix

Majician provides an extensive collection of elevated tweaks organized across specialized categories:

<div align="center">

| Category | Feature / Tweak | Shell Command Executed | Parameters / Options |
|:---|:---|:---|:---|
| 📱 **Device Identity** | Set Global Device Name | `settings put global device_name %s` | Custom string (e.g. `Pixel 9 Pro`) |
| 📱 **Device Identity** | Set Bluetooth Device Name | `settings put secure bluetooth_name %s` | Custom string |
| 📱 **Device Identity** | Set Network Hostname | `settings put global net_hostname %s` | Custom string (e.g. `pixel9pro`) |
| 🔋 **Battery** | Set Battery Level | `dumpsys battery set level %s` | Value: `0` – `100` (%) |
| 🔋 **Battery** | Set Power Source | `dumpsys battery set plugged %s` | AC (`1`), USB (`2`), Wireless (`4`), Unplugged (`0`) |
| 🔋 **Battery** | Reset Battery Status | `dumpsys battery reset` | Restores real hardware battery telemetry |
| ⚡ **Animations** | Window Animation Scale | `settings put global window_animation_scale %s` | Off (`0.0`), Fast (`0.5`), Normal (`1.0`), Slow (`1.5`), Very Slow (`2.0`) |
| ⚡ **Animations** | Transition Animation Scale | `settings put global transition_animation_scale %s` | Off (`0.0`), Fast (`0.5`), Normal (`1.0`), Slow (`1.5`) |
| ⚡ **Animations** | Animator Duration Scale | `settings put global animator_duration_scale %s` | Off (`0.0`), Fast (`0.5`), Normal (`1.0`), Slow (`1.5`) |
| 🖥️ **Display & DPI** | Screen Density (DPI) | `wm density %s` | Numerical DPI (e.g. `380`, `420`, `480`) |
| 🖥️ **Display & DPI** | Reset Screen Density | `wm density reset` | Restores OEM display density |
| 🖥️ **Display & DPI** | Screen Resolution | `wm size %s` | Width x Height (e.g. `1080x2340`) |
| 🖥️ **Display & DPI** | Reset Resolution | `wm size reset` | Restores factory resolution |
| 🖥️ **Display & DPI** | System Font Scale | `settings put system font_scale %s` | Small (`0.85`), Normal (`1.00`), Large (`1.15`), Extra Large (`1.30`) |
| 📡 **Location & Carrier**| Country Carrier (MCC/MNC)| `settings put global gsm_sim_operator_numeric %s` | 50+ Regional presets (Asia, EU, US, AF, ME) or Custom |
| 📡 **Location & Carrier**| City Location Preset | `settings put secure mock_location 1` | 30+ World cities (NY, London, Tokyo, Dubai, etc.) |
| 📡 **Location & Carrier**| Mock Location Toggle | `settings put secure mock_location %s` | Enable (`1`), Disable (`0`) |
| ⚙️ **System Tweaks** | System Dark Mode | `cmd uimode night %s` | Night mode (`yes`), Day mode (`no`) |
| ⚙️ **System Tweaks** | Immersive Full Screen | `settings put global policy_control %s` | Enable (`immersive.full=*`), Normal (`null`) |
| ⚙️ **System Tweaks** | Force Doze Idle Mode | `dumpsys deviceidle force-idle` | Triggers immediate Android Doze deep sleep |
| ⚙️ **System Tweaks** | Show Visual Touches | `settings put system show_touches %s` | Show circles (`1`), Hide (`0`) |
| ⚙️ **System Tweaks** | Stay Awake While Plugged In| `settings put global stay_on_while_plugged_in %s` | Keep Awake (`3`), Sleep Normally (`0`) |

</div>

<img src="https://user-images.githubusercontent.com/73097560/115834477-dbab4500-a447-11eb-908a-139a6edaec5c.gif" width="100%"/>

## 📁 Project Architecture

```text
majician/
├── app/
│   ├── src/main/
│   │   ├── java/com/majician/app/
│   │   │   ├── data/
│   │   │   │   ├── CommandRepository.kt      # Command registry, categories & templates
│   │   │   │   ├── LocationRepository.kt     # Global MCC/MNC & City coordinate database
│   │   │   │   ├── UpdateInfo.kt             # In-app update data model
│   │   │   │   └── UpdateManager.kt          # Cloudflare Worker OTA update consumer
│   │   │   ├── shizuku/
│   │   │   │   └── ShizukuManager.kt         # Binder lifecycle & elevated process executor
│   │   │   ├── ui/
│   │   │   │   ├── components/
│   │   │   │   │   ├── FirstStartFollowDialog.kt # Community onboarding popup
│   │   │   │   │   ├── GlassComponents.kt        # Cyber glass cards, buttons & borders
│   │   │   │   │   └── UpdateDialog.kt           # Version upgrade dialog
│   │   │   │   ├── screens/
│   │   │   │   │   ├── HomeScreen.kt         # Main tweaks hub & dialog pickers
│   │   │   │   │   └── DeveloperScreen.kt    # Developer profile & social channels
│   │   │   │   ├── theme/
│   │   │   │   │   ├── Color.kt              # Neon Cyan, Pure Blue & Dark surfaces
│   │   │   │   │   └── Theme.kt              # Compose Material3 theme definition
│   │   │   │   └── MainActivity.kt           # Edge-to-edge entry activity
│   │   │   ├── AndroidManifest.xml           # Shizuku client declarations & permissions
│   │   │   └── res/                          # Drawables, vectors, and UDMODZ assets
│   │   └── build.gradle.kts                  # App-level dependencies & Compose setup
│   └── proguard-rules.pro
├── gradle/
│   ├── libs.versions.toml                    # Version catalog (AGP 8.3.0, Kotlin 1.9.22)
│   └── wrapper/
├── build.gradle.kts                          # Root project build script
└── settings.gradle.kts                       # Repository management & project name
```

---

<div align="center">

## 👨‍💻 Creator

<a href="https://github.com/udmodz0">
  <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=700&size=28&duration=2500&pause=800&color=7C3AED&center=true&vCenter=true&repeat=true&width=300&height=50&lines=UDMODZ0;Software+Architect;Full+Stack+Developer" alt="UDMODZ0" />
</a>

<br/>

[![GitHub](https://img.shields.io/badge/GitHub-udmodz0-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/udmodz0)
[![Telegram](https://img.shields.io/badge/Telegram-@udmodz0-0088CC?style=for-the-badge&logo=telegram&logoColor=white)](https://t.me/udmodz0)
[![WhatsApp](https://img.shields.io/badge/WhatsApp-Channel-25D366?style=for-the-badge&logo=whatsapp&logoColor=white)](https://whatsapp.com/channel/0029Vb5uLwF7z4kgGnpfGU3D)
[![YouTube](https://img.shields.io/badge/YouTube-@udmodz-FF0000?style=for-the-badge&logo=youtube&logoColor=white)](https://youtube.com/@udmodz)

<br/><br/>

<img src="https://capsule-render.vercel.app/api?type=shark&color=0:4000ff,50:7c3aed,100:00d4ff&height=40&section=footer" width="100%"/>

### ⭐ Star this repo if you found it useful!

<br/>

> 🚫 **Disclaimer & License Notice**
> 
> Majician is developed for debugging, performance optimization, and testing purposes. Modifying display resolutions or critical system settings with extreme values should be performed with standard care. This package is provided as-is under the MIT License.
> 
> Made with 💜 by **UDMODZ0**

<br/>

<img src="https://github-readme-activity-graph.vercel.app/graph?username=udmodz0&theme=tokyo-night&hide_border=true&area=true&custom_title=UDMODZ0%20Contribution%20Graph" width="95%"/>

<br/>

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:4000ff,50:7c3aed,100:00d4ff&height=120&section=footer&animation=twinkling" width="100%"/>

</div>
