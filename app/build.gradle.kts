import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
}

val appPropertiesFile = file("$rootDir/app.properties")
val appProperties = Properties().apply {
    appPropertiesFile.inputStream().use { load(it) }
}

android {

    signingConfigs {
        create("finn") {
            keyAlias = "finn"
            keyPassword = appProperties["keyPassword"].toString()
            storeFile = file("../keystore/finn-keystore")
            storePassword = appProperties["keyPassword"].toString()
        }
    }

    namespace = "dev.thiaago.finn"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.thiaago.finn"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        signingConfig = signingConfigs.getByName("finn")

        resValue("string", "googleServerID", appProperties["googleServerId"].toString())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.findByName("finn")
        }
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.findByName("finn")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Androidx
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)

    // Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.lottie.compose)

    // Firebase
    implementation(libs.google.analytics)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore)
    implementation(libs.play.services.auth)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Others
    implementation(libs.google.fonts)
    implementation(libs.jetpackbrazilfields)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.compose.ui.testing)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}