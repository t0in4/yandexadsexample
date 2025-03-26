package com.github.t0in4.yandexadsexample

import android.app.Application
import com.yandex.mobile.ads.common.MobileAds
import com.yandex.mobile.ads.impl.th
import com.yandex.mobile.ads.instream.MobileInstreamAds

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) {}
        MobileInstreamAds.setAdGroupPreloading(true)
        MobileAds.enableLogging(true)
    }
}