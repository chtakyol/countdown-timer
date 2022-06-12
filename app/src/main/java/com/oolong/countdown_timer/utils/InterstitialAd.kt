package com.oolong.countdown_timer.utils

import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.oolong.countdown_timer.BuildConfig
import com.oolong.countdown_timer.utils.Utilities.findActivity

var mInterstitialAd: InterstitialAd? = null

fun loadInterstitialAd(
    context:Context
) {
    InterstitialAd.load(
        context,
        BuildConfig.TEST_INTERSTITIAL_ID,
        AdRequest.Builder().build(),
        object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
                Log.d("Admob", "onAdLoaded: Ad was loaded.")
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                mInterstitialAd = null
                Log.d("Admob", "onAdFailedToLoad: ${loadAdError.message}")
            }
        }
    )
}

fun addInterstitialCallbacks(context: Context) {
    mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
        override fun onAdDismissedFullScreenContent() {
            Log.d("Admob", "onAdDismissedFullScreenContent: Ad was dismissed")
        }

        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            Log.d("Admob", "onAdFailedToShowFullScreenContent: Ad failed to show")
        }

        override fun onAdShowedFullScreenContent() {
            mInterstitialAd = null
            loadInterstitialAd(context)
            Log.d("Admob", "onAdShowedFullScreenContent: Ad showed fullscreen content.")
        }
    }
}

fun showInterstitial(context: Context) {
    val activity = context.findActivity()

    if (mInterstitialAd != null) {
        mInterstitialAd?.show(activity!!)
    } else {
        Log.d("Admob", "showInterstitial: The interstitial ad wasn't ready yet.")
    }
}