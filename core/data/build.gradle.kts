plugins {
    id("rentalin.android.library")
    id("rentalin.android.hilt")
}

android {
    namespace = "com.rentalin.core.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    implementation(project(":core:model"))
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.junit)
}
