plugins {
    `kotlin-dsl`
}

group = "com.rentalin.buildlogic"

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "rentalin.android.application"
            implementationClass = "convention.AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "rentalin.android.application.compose"
            implementationClass = "convention.AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "rentalin.android.library"
            implementationClass = "convention.AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "rentalin.android.library.compose"
            implementationClass = "convention.AndroidLibraryComposeConventionPlugin"
        }
        register("kotlinJvm") {
            id = "rentalin.kotlin.jvm"
            implementationClass = "convention.KotlinJvmConventionPlugin"
        }
    }
}
