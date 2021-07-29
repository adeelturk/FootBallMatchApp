plugins {
    id( "com.android.library")
    id( "kotlin-android")
    id( "kotlin-kapt")
    id( "kotlin-parcelize")
    id("org.jetbrains.kotlin.plugin.serialization") version Versions.kotlin
}




dependencies {

    implementation(AppDependencies.appLibraries)

    implementation(AppDependencies.roomLibraries)
    annotationProcessor( "androidx.room:room-compiler:${Versions.room_version}")
    kapt("androidx.room:room-compiler:${Versions.room_version}")
}