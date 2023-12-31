pluginManagement {
    includeBuild("../build-logic")
}

plugins {
    id("multimodule")
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

rootProject.name = "neat"

listOf("kommander","kotlinx-interoperable").forEach {
    includeBuild("../${it}")
}

// submodules
includeSubs("neat", ".", "validation", "formatting")
