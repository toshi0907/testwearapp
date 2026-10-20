plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

// CIの実行番号(GITHUB_RUN_NUMBER)を -PbuildNumber で受け取り、単調増加するビルド番号として使う
val buildNumber = (findProperty("buildNumber") as String?)?.toIntOrNull() ?: 1

android {
    namespace = "com.toshi0907.testwearapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.toshi0907.testwearapp"
        // Pixel Watch 2 (Wear OS 4 = API 33) を対象とし、Wear OS 3 以降をサポート
        minSdk = 30
        targetSdk = 34
        versionCode = buildNumber
        versionName = "0.1.$buildNumber"
        buildConfigField("int", "BUILD_NUMBER", buildNumber.toString())
    }

    signingConfigs {
        // CIのrunnerごとに鍵が変わると上書きインストールできないため、コミット済みのデバッグ鍵で固定する
        getByName("debug") {
            storeFile = file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.wear.compose.material)
    implementation(libs.androidx.wear.compose.foundation)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
