package com.twistedgeinc.apps.tiwapa.utils

import android.Manifest

class CameraStoragePermissions {

    companion object {
        val PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
    }

}