package com.example.mydiary.presentation.custom_elements

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.mydiary.R

class FourPointGradientView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var shader: BitmapShader
    private var animationProgress = 0f

    private var colorTopLeft: Int = Color.TRANSPARENT
    private var colorTopRight: Int = Color.TRANSPARENT
    private var colorBottomLeft: Int = Color.TRANSPARENT
    private var colorBottomRight: Int = Color.TRANSPARENT


    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.FourPointGradientView)

            colorTopLeft = 
                typedArray.getColor(
                    R.styleable.FourPointGradientView_colorTopLeft, colorTopLeft
                )
            colorTopRight =  
                typedArray.getColor(
                    R.styleable.FourPointGradientView_colorTopRight, colorTopRight
                ) 
            colorBottomLeft =  
                typedArray.getColor(
                    R.styleable.FourPointGradientView_colorBottomLeft, colorBottomLeft
                ) 
            colorBottomRight =  
                typedArray.getColor(
                    R.styleable.FourPointGradientView_colorBottomRight, colorBottomRight
                ) 

            typedArray.recycle()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val bitmap = createBilinearGradientBitmap(
            w, h, colorTopLeft, colorTopRight, colorBottomLeft, colorBottomRight
        )
        shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        startAnimation()
    }
    private fun startAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 360f).apply {
            duration = 10000L
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            interpolator = LinearInterpolator()
            addUpdateListener { valueAnimator ->
                animationProgress = valueAnimator.animatedValue as Float
                val matrix = Matrix()
                val pivotX = width / 2f
                val pivotY = height / 2f
                matrix.setRotate(animationProgress, pivotX, pivotY)
                shader.setLocalMatrix(matrix)
                invalidate()
            }
            start()
        }

    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    }


}

fun createBilinearGradientBitmap(
    width: Int,
    height: Int,
    topLeft: Int,
    topRight: Int,
    bottomLeft: Int,
    bottomRight: Int,
): Bitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    for (y in 0 until height) {
        val v = y.toFloat() / (height - 1)
        for (x in 0 until width) {
            val u = x.toFloat() / (width - 1)
            val topColor = blendColors(topLeft, topRight, u)
            val bottomColor = blendColors(bottomLeft, bottomRight, u)
            val color = blendColors(topColor, bottomColor, v)
            bitmap.setPixel(x, y, color)
        }
    }
    return bitmap
}

fun blendColors(color1: Int, color2: Int, ratio: Float): Int {
    val inverseRatio = 1 - ratio
    val a = (Color.alpha(color1) * inverseRatio + Color.alpha(color2) * ratio).toInt()
    val r = (Color.red(color1) * inverseRatio + Color.red(color2) * ratio).toInt()
    val g = (Color.green(color1) * inverseRatio + Color.green(color2) * ratio).toInt()
    val b = (Color.blue(color1) * inverseRatio + Color.blue(color2) * ratio).toInt()
    return Color.argb(a, r, g, b)
}
