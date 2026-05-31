package com.dev.alt0191.shealthtweak

import com.dev.alt0191.shealthtweak.hook.DevModeHook
import com.dev.alt0191.shealthtweak.hook.RegionHook
import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit

@InjectYukiHookWithXposed(modulePackageName = "com.dev.alt0191.shealthtweak")
object HookEntry : IYukiHookXposedInit {

    override fun onInit() {
        YukiHookAPI.configs {
            debugLog {
                tag = "SHealthTweak"
                isEnable = true
                isRecord = true
            }
            isEnableHookModuleStatus = true
            isEnableHookSharedPreferences = false
        }
    }

    override fun onHook() {
        encase {
            loadApp(name = "com.sec.android.app.shealth") {
                runCatching { DevModeHook.install(this) }
                runCatching { RegionHook.install(this) }
            }
        }
    }
}

