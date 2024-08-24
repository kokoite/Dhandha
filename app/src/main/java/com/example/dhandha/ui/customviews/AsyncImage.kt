package com.example.dhandha.ui.customviews

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun AsyncImage(uri: Uri?, modifier: Modifier) {
    val request = ImageRequest.Builder(context = LocalContext.current).data(uri.toString()).build()
    val painter = rememberAsyncImagePainter(model = request)
    when (painter.state) {
        is AsyncImagePainter.State.Loading -> {
            Log.d("TAG", "AsyncImage:Loading ${uri.toString()}" )
        }
        is AsyncImagePainter.State.Error -> {
            Log.d("TAG", "AsyncImage:Loading" )
        }
        is AsyncImagePainter.State.Success -> {
            Log.d("TAG", "AsyncImage: Success")
            Image(painter = painter, contentDescription = "", modifier)
        }
        else -> {
            Log.d("TAG", "AsyncImage:Unknown" )
        }
    }
}