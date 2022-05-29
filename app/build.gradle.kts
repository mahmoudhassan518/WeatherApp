plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.mahmoud.weatherapp"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        testOptions {
            unitTests.isReturnDefaultValues = true
        }
    }

    buildTypes {

        getByName("debug") {
            isDebuggable = true
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        // We have to add the explicit cast before accessing the options itself.
        val options = this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options.jvmTarget = "1.8"
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }

    externalNativeBuild {
        cmake {
            path("CMakeLists.txt")
        }
    }
}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Kotlin.stdlib.jdk7)

    implementation(AndroidX.appCompat)
    implementation(AndroidX.constraintLayout)
    implementation(Google.android.material)
    implementation(Google.Android.PlayServices.location)

    // -------------------------------------------- lifecycle -------------------------------
    implementation(AndroidX.Core.ktx)
    implementation(AndroidX.activity.ktx)
    implementation(AndroidX.Lifecycle.viewModelKtx)
    implementation(AndroidX.Lifecycle.runtimeKtx)
    implementation(AndroidX.Lifecycle.liveDataKtx)

    // -------------------------------------------- Dagger-Hilt -------------------------------
    implementation(Google.Dagger.Hilt.Android)
    annotationProcessor(Google.Dagger.Hilt.compiler)
    annotationProcessor(AndroidX.hilt.compiler)
    kapt(Google.Dagger.Hilt.compiler)

    // --------------------- Coroutines + Flow + SharedFlow, StateFlow -------------------------
    implementation(KotlinX.coroutines.android)

    // -------------------------------------------- Logging -------------------------------
    implementation(JakeWharton.timber)

    // -------------------------------------------- retrofit -------------------------------
    implementation(Libraries.Retrofit.gson)
    implementation(Libraries.Retrofit.retrofit)
    implementation(Libraries.Retrofit.retrofitGsonConverter)

    // -------------------------------------------- okhttp3 -------------------------------
    implementation(Libraries.Okhttp.okhttp)
    implementation(Libraries.Okhttp.okhttpLoggingInterceptor)

    // -------------------------------------------- alerter -------------------------------
    implementation(Libraries.Utils.alerter)

//    // -------------------------------------- Test -------------------------------
//    testImplementation(Libs.Test.junit)
//    testImplementation(Libs.Test.xCore)
//    androidTestImplementation(Libs.Test.xCore)
//
//    androidTestImplementation(Libs.Test.arch)
//    androidTestImplementation(Libs.Test.rule)
//    androidTestImplementation(Libs.Test.runner)
//
//
//    testImplementation(Libs.Test.xArch)
//    androidTestImplementation(Libs.Test.xArch)
//    androidTestImplementation(Libs.Test.extJunit)
//
//    testImplementation(Libs.Test.mockito)
//    androidTestImplementation(Libs.Test.mockitoAndroid)
//
//    testImplementation(Libs.Test.mockk)
//
//
//    androidTestImplementation(Libs.Test.kaspresso)
//    androidTestImplementation(Libs.Test.kotlintest)
}
