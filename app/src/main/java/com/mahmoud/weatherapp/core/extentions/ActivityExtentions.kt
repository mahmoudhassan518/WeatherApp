package com.mahmoud.weatherapp.core.extentions

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.content.res.Configuration
import androidx.core.content.ContextCompat
import com.mahmoud.weatherapp.R
import com.tapadoo.alerter.Alerter


fun Activity.showAlerterError(
    errorMessage: String,
    errorTitle: String? = null
) {
    Alerter.create(this)
        .setIcon(R.drawable.ic_failed)
        .setTitle(errorTitle ?: getString(R.string.label_error))
        .setTitleAppearance(R.style.AlertTextAppearance_Title)
        .setText(errorMessage)
        .setTextAppearance(R.style.AlertTextAppearance_Text)
        .setBackgroundColorInt(ContextCompat.getColor(applicationContext, R.color.colorError))
        .enableSwipeToDismiss()
        .show()
}

fun Activity.showAlerterSuccess(
    errorMessage: String
) {
    Alerter.create(this)
        .setIcon(R.drawable.ic_check)
        .setTitle(getString(R.string.label_done))
        .setTitleAppearance(R.style.AlertTextAppearance_Title)
        .setText(errorMessage)
        .setTextAppearance(R.style.AlertTextAppearance_Text)
        .setBackgroundColorInt(ContextCompat.getColor(applicationContext, R.color.colorSuccess))
        .enableSwipeToDismiss()
        .show()
}

fun Activity.isTablet(): Boolean {
    return ((resources.configuration.screenLayout
            and Configuration.SCREENLAYOUT_SIZE_MASK)
            >= Configuration.SCREENLAYOUT_SIZE_LARGE)
}

fun Activity.hasCoarseLocationPermission() = ContextCompat.checkSelfPermission(
    this,
    Manifest.permission.ACCESS_COARSE_LOCATION
) == PackageManager.PERMISSION_GRANTED

fun Activity.hasFineLocationPermission() = ContextCompat.checkSelfPermission(
    this,
    Manifest.permission.ACCESS_FINE_LOCATION
) == PackageManager.PERMISSION_GRANTED