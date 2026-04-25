//用于配置项目模块和仓库信息
pluginManagement {
    repositories {
        // 1. Android插件、Kotlin插件的核心仓库
        google()
        // 2. 全球公共依赖仓库（包含LSPosed依赖）
        mavenCentral()
        // 3. Gradle官方插件仓库（Kotlin插件的分发源）
        gradlePluginPortal()
        // 4. 额外补充：JitPack（防止部分依赖缺失）
        maven("https://jitpack.io")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // 与pluginManagement保持一致，确保依赖下载
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "X-JT"
include(":app")
