plugins {
    id("rentalin.android.library")
}

android {
    namespace = "com.rentalin.core.data"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    implementation(project(":core:model"))
}
