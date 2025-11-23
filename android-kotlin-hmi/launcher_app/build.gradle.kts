import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import com.android.build.gradle.internal.tasks.FinalizeBundleTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.application.gradle.plugin)
    alias(libs.plugins.kotlin.android.gradle.plugin)
    alias(libs.plugins.ksp.devtools.gradle.plugin)
    alias(libs.plugins.dagger.hilt.gradle.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.automotive.bootcamp.launcher"
    compileSdk = libs.versions.sdk.target.get().toInt()

    defaultConfig {
        applicationId = "com.automotive.bootcamp.launcher"
        minSdk = libs.versions.sdk.minimal.get().toInt()

        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        externalNativeBuild {
            cmake {
                arguments += listOf("-DANDROID_SUPPORT_FLEXIBLE_PAGE_SIZES=ON")
                /// here is possible to add some flags for C++ compiler:
                /// cppFlags += "-std=c++17"
                /// cppFlags += "-fno-rtti"
                /// cppFlags += "-fexceptions"
            }
        }
    }

    externalNativeBuild {
        cmake {
            version = "3.22.1"
            path = file("src/main/cpp/CMakeLists.txt")

        }
    }

    applicationVariants.all {
        val variant = this

        variant.outputs.all {
            this as ApkVariantOutputImpl
            outputFileName = "app-${variant.name}-$versionName-$versionCode.apk"
        }

        tasks.named("sign${variant.name.capitalize()}Bundle", FinalizeBundleTask::class) {
            val artifact = finalBundleFile.asFile.get()
            val outputFileName =
                File(artifact.parentFile, "app-${variant.name}-$versionName-${versionCode}.aab")
            finalBundleFile.set(outputFileName)
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
        BuildTypes.forEach {
            ensureBuildTypeBy(it.type.gradleName()) {
                applicationIdSuffix = it.applicationIdSuffix

                it.resValues.forEach { (type, name, value) -> resValue(type, name, value) }
                it.buildConfigFields.forEach { (type, name, value) ->
                    buildConfigField(
                        type,
                        name,
                        value
                    )
                }

                isMinifyEnabled = it.isMinifyEnabled
                isShrinkResources = it.isShrinkResources
                if (it.isProGuardEnabled) {
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        checkDependencies = true
        targetSdk = libs.versions.sdk.target.get().toInt()
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

    implementation(projects.features.example)
    implementation(projects.features.adas)
    implementation(projects.features.status)
    implementation(projects.features.chargingPreferences)
    implementation(projects.features.climate)
    implementation(projects.features.vcis)
    implementation(projects.features.userprofiles)

    implementation(libs.bundles.androidx)
    implementation(libs.bundles.networking)
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.foundation.layout.android)

    ksp(libs.dagger.hilt.compiler)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
}
