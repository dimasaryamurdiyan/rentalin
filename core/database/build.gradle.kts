plugins {
    id("rentalin.android.library")
    id("rentalin.android.hilt")
    id("rentalin.android.room")
}

android {
    namespace = "com.rentalin.core.database"
}

dependencies {
    implementation(project(":core:model"))
}
