package com.lbxq.screen

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class XposedModule : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        // 只对微信生效（包名com.tencent.mm）
        if (lpparam.packageName != "com.tencent.mm") return

        XposedBridge.log("📱 开始解除微信隐私保护模式")

        // Hook Window.setFlags方法
        XposedHelpers.findAndHookMethod(
            "android.view.Window",
            lpparam.classLoader,
            "setFlags",
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType,
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    val flags = param.args[0] as Int
                    // 移除FLAG_SECURE标记（禁止截图的关键标记）
                    if (flags and 0x00002000 != 0) { // 0x00002000是FLAG_SECURE的常量值
                        param.args[0] = flags and 0x00002000.inv() // 移除该标记
                        XposedBridge.log("✅ 已解除微信隐私保护模式（禁止截图）")
                    }
                }
            }
        )
    }
}
