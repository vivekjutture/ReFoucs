# Refocus 🧠📱

**A focus-first Android app to reduce short-form video distractions**

---

## 🚧 Project Status: In Progress

⚠️ Refocus is **actively under development** and **not finished yet**.  
Features, behavior, and implementation details may change as experimentation continues.

---

## 🎯 Motivation

Short-form video feeds like **YouTube Shorts** and **Instagram Reels** are engineered for endless engagement.  
Refocus is built for users who want to consume **long-form content intentionally** and reduce distraction — without rooting, patching, or modifying third-party apps.

This project explores what is **realistically possible** within official Android and Google Play Store boundaries.

---

## ✨ Core Principles

✅ Play Store compliant  
🔒 No root access  
🧩 No patched or modified apps  
🚫 No sign-in or sign-up  
📵 No data collection  
📊 No analytics or tracking  
📦 Local-only processing  
🔍 Transparent use of system APIs

---

## 🧠 Technical Approach (Experimental)

Refocus experiments with **focus-oriented behavioral control** using official Android system APIs.

Main components used:

🧩 Android AccessibilityService  
💾 Jetpack DataStore for local preferences

Deliberately **NOT used**:

❌ Text labels  
❌ Content descriptions  
❌ View IDs  
❌ URLs  
❌ Network inspection

Multiple approaches were tested and discarded after **real-device testing**.  
This repository documents **learning and experimentation**, not a guaranteed solution.

---

## 🚧 Current Limitations

Refocus **cannot**:

❌ Reliably remove Shorts from YouTube feeds  
❌ Block ads inside YouTube  
❌ Inspect video URLs or IDs inside the YouTube app  
❌ Guarantee complete suppression of short-form content

These are **intentional platform limitations**, not implementation bugs.

---

## 🔮 Future Scope

📵 Improved focus-based prevention of YouTube Shorts  
📸 Support for **Instagram Reels**  
🎛️ Per-app focus and restriction rules  
🎨 UX improvements and clearer user education  
🚀 Intent-based or launcher-assisted flows  
🧩 Jetpack Compose UI migration  
🧪 Continued real-device experimentation

---

## ⚙️ Project Setup

### 🧰 Requirements

🖥️ Android Studio (latest stable)  
📱 Android SDK 24+  
💬 Kotlin  
📲 A **real Android device** (Accessibility is unreliable on emulators)

### 🚀 Setup Steps

1. **Clone the repository**

   ```bash
   git clone https://github.com/vivekjutture/refocus.git
   ```

2. **Open** the cloned folder in **Android Studio**

3. **Sync Gradle :** Allow Android Studio to complete Gradle sync

4. **Run the app :** Connect a real device and click **Run ▶**

---

## 📚 Dependencies Used

### 📦 Where dependencies are added

All dependencies are added in **app/build.gradle**

- Jetpack DataStore (Preferences)

  ```bash
  implementation("androidx.datastore:datastore-preferences:1.0.0")
  ```

🚫 No analytics, ads, or networking libraries are used.

---

## 🔐 Permissions & Accessibility

### 📄 Where permissions are declared

Permissions are declared in **AndroidManifest.xml**

### 🔑 Accessibility Service permission

```bash
<uses-permission android:name="android.permission.BIND_ACCESSIBILITY_SERVICE" />
```

### 🧩 Accessibility Service declaration

```bash
<service
    android:name=".RefocusAccessibilityService"      android:permission="android.permission.BIND_ACCESSIBILITY_SERVICE"
    android:exported="false">
```

### 📜 Accessibility configuration reference

`android:resource="@xml/accessibility_service_config"`

---

## 🔍 Why Accessibility is used

Accessibility is used **only** to:

👀 Observe app navigation events  
🛑 Enforce focus-related behavior  
📵 Prevent entry into short-form video experiences

Important guarantees:

🔒 No content is read or stored  
🌐 No data leaves the device  
📜 Usage is disclosed and Play Store compliant

---

## 🗂️ Project Structure

```bash
app/
├── src/main/
│   ├── java/com/example/refocus/
│   │   ├── MainActivity.kt                         # App entry point
│   │   ├── RefocusAccessibilityService.kt          # Accessibility logic
│   │   ├── RefocusDataStore.kt                     # Preference storage
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml                   # Main UI layout
│   │   ├── xml/
│   │   │   └── accessibility_service_config.xml    # Accessibility service config
│   │   ├── values/
│   │   │   ├── strings.xml                         # App strings
│   │   │   └── themes.xml                          # App theme
│   │   ├── values-night/
│   │   │   └── themes.xml                          # Dark theme
│   └── AndroidManifest.xml                         # Permissions & services
└── build.gradle                                    # App-level dependencies
```

---

## 🤝 Contributing

Contributions are welcome and encouraged.

- Fork the project
- Create your Feature Branch `git checkout -b feature/NewFeature`
- Commit your changes `git commit -m "Add New Feature"`
- Push to the branch `git push origin feature/NewFeature`
- Open a Pull Request

Please read the full contribution guidelines here: 👉 **[CONTRIBUTING.md](CONTRIBUTING.md)**

---

## 🔐 Privacy

Refocus does **not collect, store, or transmit any personal data**.  
Read the full privacy policy here: 👉 **[PRIVACY_POLICY.md](PRIVACY_POLICY.md)**

---

## 📄 License

This project is licensed under the **Apache License 2.0**.
👉 **[LICENSE](LICENSE)**

---

## 🚫 Out-of-Scope Contributions

❌ Root-only solutions  
❌ Patched or modified third-party apps  
❌ DRM or ad-bypass techniques  
❌ Hidden or deceptive behavior

---

## 🔐 Accessibility & Transparency

🔒 No user data collection  
🌐 No network calls  
📊 No analytics  
🕵️ No background surveillance

Accessibility usage exists **only** to support focus-related behavior.

---

## ⚠️ Disclaimer

Refocus is:
❗ Not affiliated with Google, YouTube, Meta, or any other platform  
❗ Not a YouTube mod or ad blocker  
❗ Not guaranteed to block all short-form content

If your goal is complete removal of Shorts and ads, patched clients or alternative front-ends may be more effective — but those are **out of scope by design**.

---

## 🧭 Why This Repository Exists

🧠 Explore the real limits of Android system APIs  
📚 Document what works and what doesn’t  
🛠️ Share honest engineering lessons  
🌱 Encourage healthier, more intentional content consumption

Refocus prioritizes **legality, transparency, and sustainability** over hacks or shortcuts.
