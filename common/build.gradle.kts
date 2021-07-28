plugins {
    id( "com.android.library")
    id( "kotlin-android")
    id( "kotlin-kapt")
}




dependencies {

    implementation(AppDependencies.appLibraries)
    implementation(AppDependencies.retrofitLibraries)
    implementation(AppDependencies.coroutinesLibrary)
    implementation(AppDependencies.imageLibs)

}