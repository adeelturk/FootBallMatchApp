plugins {
    id( "com.android.library")
    id( "kotlin-android")
    id( "kotlin-kapt")
}
dependencies {

    implementation(AppDependencies.appLibraries)
    implementation(AppDependencies.imageLibs)

    implementation(project(":common"))
    implementation(project(":dtos"))
    implementation(project(":business"))


}
kapt {
    generateStubs = true
}