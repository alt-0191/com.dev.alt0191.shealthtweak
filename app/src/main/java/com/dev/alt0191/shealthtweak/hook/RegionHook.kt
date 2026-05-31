package com.dev.alt0191.shealthtweak.hook

import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.factory.toClass
import com.highcapable.yukihookapi.hook.param.PackageParam

object RegionHook {

    fun install(param: PackageParam) {
        param.apply {
            try {
                val fmClass = "com.samsung.android.app.shealth.config.FeatureManager".toClass()
                val fm = fmClass.getDeclaredMethod("getInstance").invoke(null)
                val setStr = fmClass.getDeclaredMethod("setStringValue", String::class.java, String::class.java)
                setStr.invoke(fm, "COMMON_CSC", "KR")
                setStr.invoke(fm, "COMMON_MCC", "450")
            } catch (_: Exception) {}

            "com.samsung.android.sdk.healthdata.privileged.AccountOperation".toClass().apply {
                method {
                    name = "isChinaConsentGranted"
                    param(android.content.Context::class.java)
                }.hook { replaceAny { true } }
                method {
                    name = "isSyncLegal"
                    param(String::class.java, String::class.java)
                }.hook { replaceAny { true } }
                method { name = "isBuildVariantChina" }
                    .hook { replaceAny { true } }
            }

            "com.samsung.android.sdk.healthdata.privileged.util.AccountUtil".toClass()
                .method { name = "isChina" }
                .hook { replaceAny { false } }

            "p000.dv2".toClass().apply {
                method {
                    name = "m25190a"
                    param(android.content.Context::class.java)
                }.hook { replaceAny { "KR" } }
                method {
                    name = "m25193d"
                    param(android.content.Context::class.java)
                }.hook { replaceAny { false } }
                method {
                    name = "m25196g"
                    param(android.content.Context::class.java)
                }.hook { replaceAny { false } }
            }
        }
    }
}
