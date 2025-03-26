package com.github.t0in4.yandexadsexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.t0in4.yandexadsexample.ui.theme.YandexAdsExampleTheme
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YandexAdsExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun YandexBannerAd() {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val density = LocalDensity.current
    val screenWidthPx = with(density) { screenWidthDp.dp.toPx() }
    AndroidView(
        modifier = Modifier
            .fillMaxWidth(),
        factory = {
            it ->
            BannerAdView(it).apply {
                setAdSize(
                    BannerAdSize.stickySize(
                        context = context,
                        width = screenWidthPx.toInt()
                    )
                )
                setAdUnitId("demo-banner-yandex")
                setBannerAdEventListener(object: BannerAdEventListener {
                    override fun onAdClicked() {
                        println(">>> YandexAds onAdClicked")
                    }

                    override fun onAdFailedToLoad(error: AdRequestError) {
                        println(">>> YandexAds onAdFailedToLoad: $error")
                    }

                    override fun onAdLoaded() {
                        println(">>> YandexAds onAdLoaded")
                    }

                    override fun onImpression(impressionData: ImpressionData?) {
                        println(">>> YandexAds onImpression" + impressionData?.rawData)
                    }

                    override fun onLeftApplication() {
                        println(">>> YandexAds onLeftApplication")
                    }

                    override fun onReturnedToApplication() {
                        println(">>> YandexAds onReturnedToApplication")
                    }

                })
                loadAd(
                    AdRequest.Builder()
                        .build()
                )
            }
        }
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column{
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .size(100.dp)
        ){
            YandexBannerAd()
        }
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    YandexAdsExampleTheme {
        Greeting("Android")
    }
}