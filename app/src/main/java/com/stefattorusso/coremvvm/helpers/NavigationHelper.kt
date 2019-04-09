package com.stefattorusso.coremvvm.helpers

import android.app.Activity
import android.content.Intent
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.stefattorusso.coremvvm.ui.camera.CameraActivity
import com.stefattorusso.coremvvm.ui.detail.DetailActivity
import com.stefattorusso.coremvvm.ui.grid.GridActivity
import com.stefattorusso.coremvvm.ui.home.HomeActivity
import com.stefattorusso.coremvvm.ui.location.LocationActivity
import com.stefattorusso.coremvvm.ui.login.LoginActivity
import com.stefattorusso.domain.Image

class NavigationHelper(
    private val activity: Activity
) {

    fun launchHome() {
        launch(Intent(activity, HomeActivity::class.java))
    }

    fun launchHomeAndFinishCurrent() {
        launchAndFinish(Intent(activity, HomeActivity::class.java))
    }

    fun launchMain() {
        launch(Intent(activity, GridActivity::class.java))
    }

    fun launchDetailWithTransactionAnimation(view: ImageView, image: Image) {
        launchWithAnimation(view, Intent(activity, DetailActivity::class.java).apply {
            putExtra(Image::class.java.simpleName, image)
        })
    }

    fun launchCameraView() {
        launch(Intent(activity, CameraActivity::class.java))
    }

    fun launchLocationView() {
        launch(Intent(activity, LocationActivity::class.java))
    }

    fun launchLoginView() {
        launchAndFinish(Intent(activity, LoginActivity::class.java))
    }

    private fun launch(intent: Intent) {
        activity.startActivity(intent)
    }

    private fun launchAndFinish(intent: Intent) {
        activity.startActivity(intent)
        activity.finish()
    }

    private fun launchWithAnimation(view: ImageView, intent: Intent) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, view.transitionName)
        activity.startActivity(intent, options.toBundle())
    }
}
