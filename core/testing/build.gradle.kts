plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}
android {

    namespace = "com.falmeida.core.testing"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
    sourceSets["main"].java.srcDirs("src/main/kotlin")
}

dependencies {
    // Add test dependencies you want shared
    implementation(kotlin("stdlib"))
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.junit)
    implementation(libs.mockk)
    implementation(projects.core)
}