import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.itis.mvicore"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.itis.mvicore"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    val keystoreProperties = Properties()
    keystoreProperties.load(FileInputStream(rootProject.file("keys.properties")))
    buildTypes.forEach {
        it.buildConfigField("String", "API_ENDPOINT", keystoreProperties.getProperty("baseUrl"))
        it.buildConfigField("String", "API_KEY", keystoreProperties.getProperty("apiKey"))
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    val koin = "3.4.0"
    implementation("io.insert-koin:koin-android:${koin}")
    implementation("io.insert-koin:koin-androidx-compose:$koin")

    // region MVICore
    val mviCoreVersion = "1.4.0"
    implementation("com.github.badoo.mvicore:mvicore:${mviCoreVersion}")
    implementation("com.github.badoo.mvicore:binder:${mviCoreVersion}")
    implementation("com.github.badoo.mvicore:mvicore-android:${mviCoreVersion}")
    // endregion

    // region Network
    val retrofit = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofit")

    val okhttp = "4.10.0"
    implementation("com.squareup.okhttp3:okhttp:$okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp")

    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    // endregion

    implementation("io.coil-kt:coil:2.2.2")
    implementation("io.coil-kt:coil-compose:2.3.0")

    // region rxJava

    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.4.3")
    // endregion
}
