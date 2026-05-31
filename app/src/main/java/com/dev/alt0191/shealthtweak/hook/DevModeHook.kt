package com.dev.alt0191.shealthtweak.hook

import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.factory.toClass
import com.highcapable.yukihookapi.hook.param.PackageParam

object DevModeHook {

    fun install(param: PackageParam) {
        param.apply {
            // FeatureManager 文件检测
            "com.samsung.android.app.shealth.config.FeatureManager".toClass().apply {
                method { name = "isEnabled" }.hook { replaceAny { true } }
                method { name = "isCscFeatureEnabled" }.hook { replaceAny { true } }
            }

            // 关于页入口
            "com.samsung.android.sdk.health.sdkpolicy.SdkDevPolicyControl\$Companion".toClass()
                .method {
                    name = "isDeveloperModeOn"
                    param(android.content.Context::class.java)
                }
                .hook { replaceAny { true } }

            // 跳过 dev key 弹窗
            "com.samsung.android.app.shealth.config.FeatureListActivity".toClass()
                .method {
                    name = "handleDeveloperModeEnable"
                    param(String::class.java, android.widget.RadioGroup::class.java)
                }
                .hook {
                    before {
                        val key = args[0] as String
                        try {
                            val fmClass = "com.samsung.android.app.shealth.config.FeatureManager".toClass()
                            val fm = fmClass.getDeclaredMethod("getInstance").invoke(null)
                            fmClass.getDeclaredMethod("setStringValue", String::class.java, String::class.java)
                                .invoke(fm, key, "on")
                        } catch (_: Exception) {}
                        resultNull()
                    }
                }
        }
    }
}
