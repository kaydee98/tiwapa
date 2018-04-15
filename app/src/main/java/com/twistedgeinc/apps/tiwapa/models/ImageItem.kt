package com.twistedgeinc.apps.tiwapa.models

class ImageItem {

    var imageData: String = ""
    var displayName: String = ""
    var createDate: String = ""

    fun ImageItem(imageData: String, displayName: String, createDate: String) {
        this.imageData = imageData
        this.displayName = displayName
        this.createDate = createDate
    }

}