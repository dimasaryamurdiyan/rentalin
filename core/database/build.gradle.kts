plugins {
    id("rentalin.android.library")
}

android {
    namespace = "com.rentalin.core.database"
}

dependencies {
    implementation(project(":core:model"))
}
