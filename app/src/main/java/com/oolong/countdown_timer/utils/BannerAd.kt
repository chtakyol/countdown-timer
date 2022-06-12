package com.oolong.countdown_timer.utils

import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.oolong.countdown_timer.BuildConfig

@RequiresPermission("android.permission.INTERNET")
@Composable
fun BannerAd(
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth(),
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = BuildConfig.TEST_BANNER_ID
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}