package com.example.smarttask.utility;

import android.app.Activity
import android.content.Context
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability


class InAppUpdateClient
constructor(context: Context) : InstallStateUpdatedListener {


    companion object {
        const val INAPPUPDATE_REQUEST_CODE = 101
    }


    val appUpdateManager = AppUpdateManagerFactory.create(context)

    private var appUpdateInfoTask = appUpdateManager.appUpdateInfo
    var onResult: ((result: UpdateState) -> Unit)? = null


    /**
     * This function checks the availability of update with play store
     *
     */
    fun checkUpdateAvailable(activity: Activity) {
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            // if update is available and is immediate
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE

                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                startUpdate(appUpdateInfo, activity)
            }
        }

    }

    /**
     * start update
     */
    fun startUpdate(appUpdateInfo: AppUpdateInfo, activity: Activity) {

        appUpdateManager.startUpdateFlowForResult(
            appUpdateInfo,
            AppUpdateType.IMMEDIATE,
            activity,
            INAPPUPDATE_REQUEST_CODE

        )
    }


    /**
     * This method will be called on activity result,
     * to check whether update is accepted or rejected
     */
    fun onActivityResult(requestCode: Int, resultCode: Int) {
        if (requestCode == INAPPUPDATE_REQUEST_CODE) {

            if (resultCode == Activity.RESULT_OK) {
                // onResult?.invoke(UpdateState.UPDATE_ACCEPTED)
                appUpdateManager.registerListener(this)
            } else {
                onResult?.invoke(UpdateState.FAILED)
            }
        }
    }


    enum class UpdateState {
        SUCCESS,
        FAILED,
        CANCELLED,
        INSTALLING

    }


    //
    fun completeUpdate() {
        appUpdateManager.completeUpdate()
    }

    override fun onStateUpdate(state: InstallState) {
        when (state?.installStatus()) {

            InstallStatus.DOWNLOADED -> completeUpdate()
            InstallStatus.CANCELED -> onResult?.invoke(UpdateState.CANCELLED)
            InstallStatus.INSTALLING -> onResult?.invoke(UpdateState.INSTALLING)
            InstallStatus.FAILED -> onResult?.invoke(UpdateState.FAILED)
            InstallStatus.INSTALLED -> {
                onResult?.invoke(UpdateState.SUCCESS)
                appUpdateManager.unregisterListener(this)
            }

        }
    }
}