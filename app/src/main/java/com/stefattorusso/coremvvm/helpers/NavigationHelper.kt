package com.stefattorusso.coremvvm.helpers

import android.app.Activity
import android.content.Intent
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.stefattorusso.coremvvm.ui.detail.DetailActivity
import com.stefattorusso.coremvvm.ui.main.MainActivity
import com.stefattorusso.domain.Image

class NavigationHelper(
    private val activity: Activity
) {

    fun launchMain() {
        launchAndFinish(Intent(activity, MainActivity::class.java))
    }

    fun launchDetailWithTransactionAnimation(view: ImageView, image: Image) {
        launchWithAnimation(view, Intent(activity, DetailActivity::class.java).apply {
            putExtra(Image::class.java.simpleName, image)
        })
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
