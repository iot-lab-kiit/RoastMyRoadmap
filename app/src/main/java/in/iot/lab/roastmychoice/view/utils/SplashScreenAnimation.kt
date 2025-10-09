package `in`.iot.lab.roastmychoice.view.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.splashscreen.SplashScreenViewProvider

fun setupSplashScreenExitAnimation(splashScreenViewProvider: SplashScreenViewProvider) {
    val scaleValueMin = 1f
    val scaleValueMax = 3f
    val animationDuration = 3000L

    val scaleX = ObjectAnimator.ofFloat(
        splashScreenViewProvider.iconView,
        View.SCALE_X,
        scaleValueMin,
        scaleValueMax
    ).apply {
        duration = animationDuration
        interpolator = AnticipateInterpolator()
    }

    val scaleY = ObjectAnimator.ofFloat(
        splashScreenViewProvider.iconView,
        View.SCALE_Y,
        scaleValueMin,
        scaleValueMax
    ).apply {
        duration = animationDuration
        interpolator = AnticipateInterpolator()
    }

    scaleX.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            splashScreenViewProvider.remove()
        }
    })

    scaleX.start()
    scaleY.start()
}
