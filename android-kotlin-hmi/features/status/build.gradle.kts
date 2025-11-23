import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.library.gradle.plugin)
    alias(libs.plugins.kotlin.android.gradle.plugin)
    alias(libs.plugins.ksp.devtools.gradle.plugin)
    alias(libs.plugins.dagger.hilt.gradle.plugin)
    alias(libs.plugins.kotlin.parcelize.gradle.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.automotive.bootcamp.status"
    compileSdk = libs.versions.sdk.target.get().toInt()

    defaultConfig {
        minSdk = libs.versions.sdk.minimal.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        BuildTypes.forEach { ensureBuildTypeBy(it.type.gradleName()) }
    }

    buildFeatures {
        aidl = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        checkDependencies = true
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

hilt {
    enableAggregatingTask = false
}

dependencies {
    api(projects.common)
    implementation(libs.bundles.androidx)

    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)

    androidTestImplementation(libs.androidx.junit.ktx)
}
