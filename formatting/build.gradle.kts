plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

description = "A platform agnostic way of formatting data"

kotlin {
    jvm { library() }
    if (Targeting.JS) js(IR) { library() }
    if (Targeting.WASM) wasm { library() }
    if (Targeting.OSX) osxTargets() else listOf()
    if (Targeting.NDK) ndkTargets() else listOf()
    if (Targeting.LINUX) linuxTargets() else listOf()
    if (Targeting.MINGW) mingwTargets() else listOf()
    
    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(libs.kommander.core)
            }
        }
    }
}