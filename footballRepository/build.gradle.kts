plugins {
    id( "com.android.library")
    id( "kotlin-android")
    id( "kotlin-kapt")
}




dependencies {

    implementation(AppDependencies.appLibraries)
    implementation(AppDependencies.retrofitLibraries)
    implementation(AppDependencies.roomLibraries)
    annotationProcessor( "androidx.room:room-compiler:${Versions.room_version}")
    kapt("androidx.room:room-compiler:${Versions.room_version}")
    implementation(project(":common"))
    implementation(project(":network"))
    implementation(project(":dtos"))

}