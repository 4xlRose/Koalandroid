plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    alias(libs.plugins.compose.compiler)
}



android {
    namespace = "com.example.koadex"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.koadex"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        manifestPlaceholders.clear()
        manifestPlaceholders["auth0Domain"] = "@string/com_auth0_domain"
        manifestPlaceholders["auth0Scheme"] = "https"

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests.all {
            it.jvmArgs!! += "-noverify"
            it.jvmArgs!! += "-Xmx1024m"

        }
    }
}


dependencies{
    implementation(libs.hilt.android)
    /*Room*/
    ksp(libs.androidx.room.compiler.v250)
    implementation(libs.room.runtime)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.room)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    /*Original*/
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.navigation.compose)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    /*Otro*/
    implementation("androidx.compose.material:material-icons-extended:1.5.1")
    /*Auth0*/
    implementation(libs.auth0)

    // Otras dependencias de prueba necesarias
    //testImplementation(libs.mockk.v11313)
    androidTestImplementation(libs.androidx.core)
    androidTestImplementation (libs.mockk.android.v1123)
    //androidTestImplementation (libs.mockk.android.v11313)
    testImplementation (libs.androidx.core.testing.v220)
    testImplementation (libs.mockito.android)
    testImplementation (libs.mockito.core.v481)
    testImplementation (libs.mockito.mockito.inline.v400)
    testImplementation (libs.mockito.kotlin.v410)

    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.junit.v113)
    androidTestImplementation (libs.androidx.espresso.core.v340)
    //testImplementation (libs.androidx.core.testing.v220)

    testImplementation (libs.kotlinx.coroutines.test.v190)


    // Prueba unitaria
    implementation (platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.06.01"))
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")


}
