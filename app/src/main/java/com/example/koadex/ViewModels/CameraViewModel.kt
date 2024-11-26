package com.example.cameraexample.ViewModels

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraController
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import java.io.File


class CameraViewModel: ViewModel() {
    private var imageCapture: CameraController? = null


    fun setImageCapture(imageCapture: CameraController) {
        this.imageCapture = imageCapture
    }

    /*@RequiresApi(Build.VERSION_CODES.P)
    fun takePhoto(
        context: Context,
        onImageSaved: (File) -> Unit,
        onError: (ImageCaptureException) -> Unit
    ) {
        // Crear archivo para guardar la imagen
        val photoFile = File(
            context.getExternalFilesDir(null),
            "IMG_${System.currentTimeMillis()}.jpg"
        )

        // Configurar opciones de salida
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        // Capturar la imagen
        imageCapture?.takePicture(
            outputOptions,
            context.mainExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    onImageSaved(photoFile)
                }

                override fun onError(exception: ImageCaptureException) {
                    onError(exception)
                }
            }
        )
    }*/

    @RequiresApi(Build.VERSION_CODES.P)
    fun takePhoto(
        context: Context,
        onImageSaved: (File) -> Unit,
        onError: (ImageCaptureException) -> Unit
    ) {
        // Create a ContentValues object to describe the image metadata
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "IMG_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(
                MediaStore.Images.Media.RELATIVE_PATH,
                "Pictures/CameraExample"
            ) // Store in "Pictures/CameraExample" directory
        }


    }
}