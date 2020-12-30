package com.ramonbarros.mylyricsapp.view.component

import android.graphics.*
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ramonbarros.mylyricsapp.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlin.math.min

@BindingAdapter("imageUrl")
fun loadImage(component: ImageView, imageUrl: String?) {
    Picasso.get()
        .load(imageUrl)
        .transform(CircleTransform())
        .placeholder(R.drawable.placeholder_avatar)
        .into(component)
}

private class CircleTransform : Transformation {
    override fun transform(source: Bitmap): Bitmap {
        val size = min(source.width, source.height)
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2
        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) { source.recycle() }
        val bitmap = Bitmap.createBitmap(size, size, source.config)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.isAntiAlias = true
        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)
        squaredBitmap.recycle()
        return bitmap
    }

    override fun key() = "circle"
}
