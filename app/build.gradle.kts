//Android项目中 app 模块的构建配置文件
plugins {
    id("com.android.application") version "8.1.0"
    id("org.jetbrains.kotlin.android") version "1.9.22"
}

android {
    namespace = "com.lbxq.screen"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.lbxq.screen"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.6"
    }
    buildTypes { 
        release { 
            isMinifyEnabled = false 
        }
    }
    compileOptions { 
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17 
    } // 补充这行大括号闭合compileOptions
    kotlinOptions { 
        jvmTarget = "17" 
    }
}

dependencies {
    //implementation("org.lsposed.api:api:82") // 适配LSPosed 1.8+的版本
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.22")
    // 新增：添加Xposed的编译时依赖（不会打包到APK）
    compileOnly("de.robv.android.xposed:api:82")
    compileOnly("de.robv.android.xposed:api:82:sources")
}
