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
}

dependencies{
    implementation(libs.junit.junit)
    implementation(libs.androidx.core.testing)
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

    // Dependencia para pruebas con coroutines
    testImplementation (libs.kotlinx.coroutines.test)

    // Otras dependencias de prueba necesarias
    androidTestImplementation (libs.mockk.android.v1123)

    testImplementation(libs.mockk.v1135)


    testImplementation (libs.junit) // Versión actual de JUnit 4
    androidTestImplementation (libs.androidx.junit.v113) // JUnit para pruebas de Android
    androidTestImplementation (libs.androidx.espresso.core.v340)

    // Para pruebas de coroutines, si usas coroutines en tu ViewModel
    testImplementation (libs.kotlinx.coroutines.test) // Actualiza según tu versión

    testImplementation (libs.androidx.core.testing.v220)

    testImplementation (libs.kotlinx.coroutines.test.v170) // Para coroutines en pruebas
    testImplementation (libs.mockk.jvm) // Dependencia para soporte JVM (si es necesario)

    testImplementation(libs.mockk.android)



}


