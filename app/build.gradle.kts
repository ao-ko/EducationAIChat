plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.chatuiplaygroud"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.chatuiplaygroud"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.github.timigod:android-chat-ui:v0.1.4")
    implementation ("dev.langchain4j:langchain4j-open-ai:0.33.0")
    implementation ("dev.langchain4j:langchain4j:0.33.0")
    implementation ("com.google.code.gson:gson:2.11.0")
}

