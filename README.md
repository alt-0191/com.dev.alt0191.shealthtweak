# SHealth Tweak

三星健康 Xposed 模块。安装后在 LSPosed 勾选 `com.sec.android.app.shealth` 即可，无需手动创建文件夹或输入 developer key。

Xposed module for Samsung Health. Enable in LSPosed and check `com.sec.android.app.shealth`. No need to create folders or provide a developer key.

新版本三星健康隐藏 FeatureList 需要 developer key 验证，本模块跳过全部验证直接开启。 / New Samsung Health versions hide FeatureList behind developer key validation. This module bypasses all validation.

**使用方法 / Usage**：Settings → About Samsung Health → SetFeatures

```bash
./gradlew assembleDebug
```

Samsung Health v6.32.0.001 / Android 9+

MIT
