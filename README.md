# Android Template Compose <br> <a href="https://github.com/chandroidx/android_template_compose/actions"><img alt="Build Status" src="https://github.com/chandroidx/android_template_compose/actions/workflows/build.yml/badge.svg"/></a><br>

## 🚀 Overview
Scalable and maintainable Android application template built with:  
- **Kotlin** 
- **Jetpack Compose**
- **MVI (Model-View-Intent) architecture**
- **Multi-module structure**

<br>

## ⚙️ Requirements
- **Android Studio Otter or newer**
- **JDK 17**

<br>

## 🗂 Project structure
```
/
├── app/                 # Appplication module 
├── data/                # Data layer interfaces & implementations
├── navigator/           # Navigation abstraction
├── presentation/        # Components shared in presentation layers
│     └── sub-modules/   # UI, ViewModels, MVI contracts
├── build-logic/         # Custom Gradle plugins & convention logic
├── buildconfig-stub/    # BuildConfig template module
├── gradle/              # Gradle wrapper & version catalogs
└── scripts/             # Utility scripts
```