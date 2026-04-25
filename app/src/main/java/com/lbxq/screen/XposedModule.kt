package com.lbxq.screen

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class XposedModule : IXposedHookLoadPackage {
    // 要处理的APP包名列表
    private val targetApps = listOf(
        "com.tencent.mm",      // 微信
        "com.ss.android.ugc.aweme", // 抖音
        "com.eg.android.AlipayGphone" // 支付宝
        "com.sankuai.meituan.dispatch.crowdsource" //美团众包
        "me.ele.crowdsource"            // 蜂鸟众包
    )

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        // 只处理列表中的APP
        if (!targetApps.any { lpparam.packageName.startsWith(it) }) return

        XposedBridge.log("📱 处理应用：${lpparam.packageName}")

        // Hook Window.setFlags解除禁止截图
        XposedHelpers.findAndHookMethod(
            "android.view.Window",
            lpparam.classLoader,
            "setFlags",
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType,
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    val flags = param.args[0] as Int
                    val FLAG_SECURE = 0x00002000
                    if (flags and FLAG_SECURE != 0) {
                        param.args[0] = flags and FLAG_SECURE.inv()
                        XposedBridge.log("✅ 解除【${lpparam.packageName}】的禁止截图限制")
                    }
                }
            }
        )
    }
}
