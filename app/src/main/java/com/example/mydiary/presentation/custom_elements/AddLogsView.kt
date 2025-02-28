package com.example.mydiary.presentation.custom_elements

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.example.mydiary.R
import com.example.mydiary.presentation.models.EmotionElementModel

class AddLogsView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint().apply {
        isAntiAlias = true
        color = Color.BLUE
    }
    private var canvasOffsetX = 0f
    private var canvasOffsetY = 0f
    private var startX = 0f
    private var startY = 0f
    private val canvasWidth = 2000f
    private val canvasHeight = 2000f
    private val elementRadius = 50f
    private var selected = Pair(-1f, -1f)
    private var smallShape = Pair(112.dpToPx(context), 112.dpToPx(context))
    private var bigShape = Pair(152.dpToPx(context), 152.dpToPx(context))
    private var divider = Pair(4.dpToPx(context), 4.dpToPx(context))

    private val emotions: List<List<EmotionElementModel>> = listOf(
        listOf(
            EmotionElementModel(
                "Ярость",
                Color.RED,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
            EmotionElementModel(
                "Зависть",
                Color.RED,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
            EmotionElementModel(
                "Выгорание",
                Color.BLUE,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
            EmotionElementModel(
                "Депрессия",
                Color.BLUE,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
        ),

        listOf(
            EmotionElementModel(
                "Напряжение",
                Color.RED,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
            EmotionElementModel(
                "Беспокойство",
                Color.RED,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
            EmotionElementModel(
                "Усталость",
                Color.BLUE,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
            EmotionElementModel(
                "Апатия",
                Color.BLUE,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
        ),

        listOf(
            EmotionElementModel(
                "Возбуждение",
                Color.YELLOW,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
            EmotionElementModel(
                "Уверенность",
                Color.YELLOW,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
            EmotionElementModel(
                "Спокойствие",
                Color.GREEN,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
            EmotionElementModel(
                "Благодарность",
                Color.GREEN,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
        ),

        listOf(
            EmotionElementModel(
                "Восторг",
                Color.YELLOW,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
            EmotionElementModel(
                "Счастье",
                Color.YELLOW,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
            EmotionElementModel(
                "Удовлетворённость",
                Color.GREEN,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
            EmotionElementModel(
                "Защищённость",
                Color.GREEN,
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                AppCompatResources.getDrawable(context, R.drawable.test_ellipse)
                    ?: throw IllegalArgumentException("problem in Add logs"),
            ),
        ),

        )
    private val smallTextPaint = Paint().apply {
        color = Color.BLACK
        textSize = 30f
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    private val bigTextPaint = Paint().apply {
        color = Color.BLACK
        textSize = 32f
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val displayMetrics = Resources.getSystem().displayMetrics
        selected = displayMetrics.widthPixels / 2f to displayMetrics.heightPixels / 2f
        val div =
            Pair((smallShape.first - bigShape.first) / 2, (smallShape.second - bigShape.second) / 2)

        val fistIndex =
            (selected.first + canvasOffsetX / (smallShape.first + divider.first)).toInt() to (selected.second + canvasOffsetY / (smallShape.second + divider.second)).toInt()


        Log.d("indexes", "fistIndex: $fistIndex")
        for (x in emotions.indices) {
            for (y in emotions[x].indices) {
                if ((selected.first == -1f && selected.second == -1f)) {

                }

                val drawable = emotions[x][y].form
                val leftBounds = x * (smallShape.first + divider.first) - canvasOffsetX
                val rightBounds = leftBounds + smallShape.first
                val topBounds = y * (smallShape.second + divider.second) - canvasOffsetY
                val bottomBounds = topBounds + smallShape.second
                drawable.apply {
                    setBounds(
                        leftBounds.toInt(),
                        topBounds.toInt(),
                        rightBounds.toInt(),
                        bottomBounds.toInt()
                    )
                    setTint(emotions[x][y].color)
                    draw(canvas)
                }
                canvas.drawText(
                    emotions[x][y].emotion,
                    ((leftBounds.toInt() + rightBounds.toInt()) / 2).toFloat(),
                    ((topBounds.toInt() + bottomBounds.toInt()) / 2).toFloat(),
                    smallTextPaint
                )

            }
        }
        paint.color = Color.GREEN
        canvas.drawCircle(selected.first, selected.second, 20f, paint)

    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                val deltaX = event.x - startX
                val deltaY = event.y - startY

                canvasOffsetX -= deltaX
                canvasOffsetY -= deltaY

                startX = event.x
                startY = event.y

                invalidate()
            }
        }
        return true
    }


    private fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }
}