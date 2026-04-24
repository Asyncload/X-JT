// 在 settings.gradle.kts 的 dependencyResolutionManagement -> repositories 块中
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // 修正 jitpack 仓库配置
        maven {
            url = uri("https://jitpack.io")
        }
        // 修正 xposed 仓库配置
        maven {
            url = uri("https://api.xposed.info/")
        }
    }
}
