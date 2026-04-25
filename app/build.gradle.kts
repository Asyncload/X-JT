#Android项目中 app 模块的构建配置文件
plugins {
    id("com.android.application") version "8.1.0" // 8.1版本插件
    id("kotlin-android") version "1.8.22" // 适配8.1插件的Kotlin版本
}

android {
    compileSdk = 34
    defaultConfig {
        applicationId = "com.lbxq.screen"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }
    buildTypes { release { isMinifyEnabled = false } }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17 }
    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    implementation("org.lsposed.api:api:1.9.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22") // 匹配Kotlin版本
}
