
# SmartInputsUZ

A minimal and efficient Uzbekistan phone number input component for Android. This library provides a pre-formatted, validated input field that strictly adheres to the Uzbekistan numbering plan (+998).

<br/>

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=flat&logo=android&logoColor=white" alt="Android" />
  <img src="https://img.shields.io/badge/Language-Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white" alt="Kotlin" />
  
  <img src="https://img.shields.io/badge/Version-0.1.0-blue?style=flat" alt="Version" />
</p>

<p align="center">
  <img src="https://img.shields.io/github/stars/uzbedeveloper/SmartInputsUZ?style=social" alt="Stars" />
  <img src="https://img.shields.io/github/forks/uzbedeveloper/SmartInputsUZ?style=social" alt="Forks" />
</p>

---

## Overview

`SmartInputsUZ` simplifies the process of collecting phone numbers in Uzbekistan-based applications. It handles the fixed country prefix, real-time visual formatting, and basic validation out of the box.

### Key Functions
* **Non-editable Prefix**: The `+998` prefix is separated from the input field to prevent user errors.
* **Automatic Formatting**: Automatically applies spaces (e.g., `90 123 45 67`) as the user types for better readability.
* **Visual Validation**: Includes a subtle success indicator that appears when a complete 9-digit number is entered.
* **Customizable Styles**: Easily adjust the prefix, hint, and accent colors via XML attributes.

---

## Installation

### 1. Add JitPack repository
Add the JitPack repository to your `settings.gradle` file:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

```

### 2. Add the dependency

Add the library to your module-level `build.gradle` file:

```kotlin
dependencies {
    implementation("com.github.uzbedeveloper:SmartInputsUZ:0.1.1")
}

```

---

## Usage

### XML Implementation

Add the `InputPhone` view to your layout file. You can customize the hint and prefix using custom attributes.

```xml
<uz.murodxonov.uzinput.InputPhone
    android:id="@+id/uzPhoneInput"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginHorizontal="20dp"
    app:uz_hint="90 123 45 67"
    app:uz_prefix="+998" />

```

### Activity Integration

The view provides simple methods to check validity and retrieve the final formatted string.

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val phoneInput = findViewById<InputPhone>(R.id.uzPhoneInput)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            if (phoneInput.isValid()) {
                // Get fully qualified E.164 formatted number (+998901234567)
                val fullNumber = phoneInput.getFullNumber()
                handleLogin(fullNumber)
            } else {
                showError("Please check your number")
            }
        }
    }
}

```

---

## Customization

The following attributes are available for customization in `attrs.xml`:

| Attribute | Format | Description |
| --- | --- | --- |
| `uz_prefix` | string | Change the displayed country prefix. |
| `uz_hint` | string | Set a custom placeholder hint. |
| `uz_accent_color` | color | Customize the focus and indicator colors. |

---

## 📸 Preview

https://github.com/user-attachments/assets/8750ad8d-9a03-4c9c-89fc-4d9362f8d11f
