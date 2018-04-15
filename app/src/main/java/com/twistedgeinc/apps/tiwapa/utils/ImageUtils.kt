package com.twistedgeinc.apps.tiwapa.utils

import android.content.Context
import android.graphics.*
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore
import com.twistedgeinc.apps.tiwapa.models.ImageItem
import java.io.ByteArrayOutputStream
import java.io.IOException


//Media Store Column "Enum"
const val COL_DISPLAY_NAME = 0
const val COL_IMAGE_DATA = 1
const val COL_DATE_TAKEN = 2

const val IMAGE_COMPRESSION_QUALITY = 50

class ImageUtils {



    fun getImageDirectories(context: Context): MutableList<String> {

        val directoryList: MutableList<String> = mutableListOf()

        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf("DISTINCT " + MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID)

        val imageCursor = context.contentResolver.query(uri, projection, null, null, null)

        if (imageCursor != null) {
            while (imageCursor.moveToNext()) {

                directoryList.add(imageCursor.getString(0)) //BUCKET_DISPLAY NAME
            }
        }

        imageCursor!!.close()

        return directoryList
    }

    fun getImages(context: Context, directory: String): MutableList<ImageItem> {

        val imageItems = ArrayList<ImageItem>()
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_TAKEN)
        val selection = MediaStore.Images.Media.BUCKET_DISPLAY_NAME + "='" + directory + "'"

        val sortby = MediaStore.Images.Media.DATE_ADDED + " Desc"

        val imageCursor = context.contentResolver.query(uri, projection, selection, null, sortby)

        if (imageCursor != null) {
            while (imageCursor.moveToNext()) {
                val display_data = imageCursor.getString(1)

                if (!display_data.contains("thumb")) {
                    val imageItem = ImageItem()
                    imageItem.displayName = imageCursor.getString(COL_DISPLAY_NAME)
                    imageItem.imageData = imageCursor.getString(COL_IMAGE_DATA)
                    imageItem.createDate = imageCursor.getString(COL_DATE_TAKEN)

                    imageItems.add(imageItem)
                }
            }
        }

        imageCursor!!.close()

        return imageItems
    }

    fun getImageFromUri(context: Context, imageUri: Uri): ImageItem {

        val imageItem = ImageItem()
        val projection = arrayOf(MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_TAKEN)

        val imageCursor = context.contentResolver.query(imageUri, projection, null, null, null)

        if (imageCursor != null) {
            while (imageCursor.moveToNext()) {
                val display_data = imageCursor.getString(1)

                if (!display_data.contains("thumb")) {
                    imageItem.displayName = imageCursor.getString(COL_DISPLAY_NAME)
                    imageItem.imageData = imageCursor.getString(COL_IMAGE_DATA)
                    imageItem.createDate = imageCursor.getString(COL_DATE_TAKEN)
                }
            }
        }

        imageCursor!!.close()

        return imageItem
    }

    /*
    Credit for image compression algorithm
        http://voidcanvas.com/whatsapp-like-image-compression-in-android/
       https://www.built.io/blog/improving-image-compression-what-we-ve-learned-from-whatsapp
    */

    fun compressImage(imagePath: String, compressionQuality: Int = IMAGE_COMPRESSION_QUALITY): ByteArray {

        var scaledBitmap: Bitmap?
        val maxHeight = 600
        val maxWidth = 800
        val maxRatio = (maxWidth / maxHeight).toFloat()

        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        var bmp = BitmapFactory.decodeFile(imagePath, options)

        var actualHeight = options.outHeight
        var actualWidth = options.outWidth

        var imgRatio = (actualWidth / actualHeight).toFloat()

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                //adjust width according to maxHeight
                imgRatio = (maxHeight / actualHeight).toFloat()
                actualWidth = imgRatio.toInt() * actualWidth
                actualHeight = maxHeight
            } else if (imgRatio > maxRatio) {
                //adjust height according to maxWidth
                imgRatio = (maxWidth / actualWidth).toFloat()
                actualHeight = imgRatio.toInt() * actualHeight
                actualWidth = maxWidth
            } else {
                actualHeight = maxHeight
                actualWidth = maxWidth
            }
        }

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight)
        options.inJustDecodeBounds = false
        options.inTempStorage = ByteArray(16 * 1024)

        try {
            bmp = BitmapFactory.decodeFile(imagePath, options)
        } catch (exception: OutOfMemoryError) {
            exception.printStackTrace()

        }

        val scaleMatrix = Matrix()
        scaleMatrix.setScale(imgRatio, imgRatio, actualWidth / 2.0f, actualHeight / 2.0f)

        scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(scaledBitmap!!)

        canvas.drawBitmap(bmp, scaleMatrix, Paint(Paint.FILTER_BITMAP_FLAG))

        //Check and adjust image orientation
        val exif: ExifInterface
        try {
            exif = ExifInterface(imagePath)

            val orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0)
            val matrix = Matrix()
            if (orientation == 6) {
                matrix.postRotate(90f)

            } else if (orientation == 3) {
                matrix.postRotate(180f)

            } else if (orientation == 8) {
                matrix.postRotate(270f)
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.width, scaledBitmap.height, matrix,
                    true)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val baos = ByteArrayOutputStream()
        scaledBitmap!!.compress(Bitmap.CompressFormat.JPEG, compressionQuality, baos)
        return baos.toByteArray()

    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
            val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
        }
        val totalPixels = (width * height).toFloat()
        val totalReqPixelsCap = (reqWidth * reqHeight * 2).toFloat()

        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++
        }

        return inSampleSize
    }
}