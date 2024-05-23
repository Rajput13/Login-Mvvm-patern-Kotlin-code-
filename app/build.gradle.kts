plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.apihit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.apihit"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding {
            enable = true
        }
        viewBinding = true
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation (libs.sdp.androidx)
    implementation (libs.ssp.android)
    var life_versions = "1.1.1"

// Lifecycle components
    implementation ("android.arch.lifecycle:extensions:$life_versions")
    annotationProcessor ("android.arch.lifecycle:compiler:$life_versions")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.1")

//Coroutains"
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0") //viewModel scope
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0") //lifecycle scope
    implementation ("androidx.fragment:fragment-ktx:1.7.0")

//Lifecycle
    implementation ("androidx.lifecycle:lifecycle-common:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
//size dp/sp

    implementation ("androidx.preference:preference-ktx:1.2.1")
    implementation ("androidx.room:room-runtime:2.2.5")

    annotationProcessor ("androidx.room:room-compiler:2.2.5")
        val paging_version = "2.1.2"

        implementation("androidx.paging:paging-runtime:$paging_version") // For Kotlin use paging-runtime-ktx

        // alternatively - without Android dependencies for testing
        testImplementation("androidx.paging:paging-common:$paging_version") // For Kotlin use paging-common-ktx

        // optional - RxJava support
        implementation("androidx.paging:paging-rxjava2:$paging_version") // For Kotlin use paging-rxjava2-ktx
    implementation ("androidx.paging:paging-runtime-ktx:3.1.0-beta01")
//optional to handle Serializable
    implementation ("com.squareup.retrofit2:converter-gson:2.5.0")
}