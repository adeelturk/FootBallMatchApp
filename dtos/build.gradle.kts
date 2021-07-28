plugins {
    id( "com.android.library")
    id( "kotlin-android")
    id( "kotlin-kapt")
    id( "kotlin-parcelize")
    id("org.jetbrains.kotlin.plugin.serialization") version Versions.kotlin
}




dependencies {

    implementation(AppDependencies.appLibraries)


}