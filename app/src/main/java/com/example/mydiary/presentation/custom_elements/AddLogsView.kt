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
import android.view.ViewConfiguration
import com.example.mydiary.presentation.models.EmotionElementModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.abs
import kotlin.math.floor

class AddLogsView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint().apply {
        isAntiAlias = true
        color = Color.BLUE
    }
    private var canvasOffsetX = 0f
    private var canvasOffsetY = 0f
    private var startX = 0f
    private var startY = 0f
    private var selected = Pair(-1f, -1f)
    var smallShape = MutableStateFlow(Pair(112.dpToPx(context), 112.dpToPx(context)))
    var bigShape = MutableStateFlow(Pair(152.dpToPx(context), 152.dpToPx(context)))
    private var divider = MutableStateFlow(Pair(4.dpToPx(context), 4.dpToPx(context)))
    private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    private var isScrolling = false

    private var alpha =
        smallShape.value.first + divider.value.first to smallShape.value.second + divider.value.second

    private var delta = selected.first / alpha.first to selected.second / alpha.second
    private var center = 0f to 0f
    private val displayMetrics = Resources.getSystem().displayMetrics

    private var _selectedItem = MutableStateFlow<EmotionElementModel?>(null)
    private var _emotions = MutableStateFlow<List<List<EmotionElementModel>>?>(null)
    val emotions: StateFlow<List<List<EmotionElementModel>>?> = _emotions


    val selectedItem: StateFlow<EmotionElementModel?> = _selectedItem
    var smallTextPaint = Paint().apply {
        color = Color.BLACK
        textSize = 30f
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    var bigTextPaint = Paint().apply {
        color = Color.BLACK
        textSize = 42f
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }


    private val div = Pair(
        (smallShape.value.first - bigShape.value.first) / 2,
        (smallShape.value.second - bigShape.value.second) / 2
    )

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        selected = displayMetrics.widthPixels / 2f to displayMetrics.heightPixels / 2f


    }

    fun setEmotions(emotions: List<List<EmotionElementModel>>) = with(this._emotions) {
        value = emotions
    }

    private var isChange = false

    private var firstStart = true
    private var lastIndex = Pair(-1, -1)

    private var maxSizeOffset = -1f to -1f

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        if (emotions.value != null) {
            if (firstStart) {
                val totalWidth = emotions.value!!.size * ((alpha.first))
                val totalHeight = emotions.value!![0].size * ((alpha.second))
                val centerGrid = totalWidth / 2f to totalHeight / 2f

                canvasOffsetX = -selected.first + centerGrid.first
                canvasOffsetY = -selected.second + centerGrid.second
                center = canvasOffsetX to canvasOffsetY
                alpha =
                    smallShape.value.first + divider.value.first to smallShape.value.second + divider.value.second
                delta = selected.first / alpha.first to selected.second / alpha.second
                maxSizeOffset = totalWidth - selected.first to totalHeight - selected.second
            }


            val fistIndex =
                floor(canvasOffsetX / alpha.first + delta.first).toInt() to floor(canvasOffsetY / alpha.second + delta.second).toInt()
            if (lastIndex != fistIndex) {
                lastIndex = fistIndex
                isChange = true
            }
            if (isChange) {
                for (x in emotions.value!!.indices) {
                    for (y in emotions.value!![x].indices) {
                        Log.d("logs_d", "onDraw")
                        Log.d("logs_d", "${fistIndex}")
                        Log.d("logs_d", "${emotions.value!!.indices}")
                        Log.d("logs_d", "${emotions.value!![x].indices}")
                        Log.d("logs_d", "--------")
                        val drawable = emotions.value!![x][y].form
                        var leftBounds =
                            x * (smallShape.value.first + divider.value.first) - canvasOffsetX
                        var rightBounds = leftBounds + smallShape.value.first
                        var topBounds =
                            y * (smallShape.value.second + divider.value.second) - canvasOffsetY
                        var bottomBounds = topBounds + smallShape.value.second
                        var selectedPaint = smallTextPaint

                        if (selectedItem.value != null) if (fistIndex.first == x && fistIndex.second == y) {
                            topBounds += div.first
                            bottomBounds -= div.first
                            leftBounds += div.first
                            rightBounds -= div.first
                            selectedPaint = bigTextPaint
                        } else {
                            if (fistIndex.first == x) {
                                if (fistIndex.second < y) {
                                    topBounds -= div.second
                                    bottomBounds -= div.second
                                }
                                if (fistIndex.second > y) {
                                    topBounds += div.second
                                    bottomBounds += div.second
                                }
                            }
                            if (fistIndex.second == y) {
                                if (fistIndex.first < x) {
                                    leftBounds -= div.first
                                    rightBounds -= div.first
                                }
                                if (fistIndex.first > x) {
                                    leftBounds += div.first
                                    rightBounds += div.first
                                }
                            }
                        }

                        drawable.apply {
                            setBounds(
                                leftBounds.toInt(),
                                topBounds.toInt(),
                                rightBounds.toInt(),
                                bottomBounds.toInt()
                            )
                            setTint(emotions.value!![x][y].color)
                            draw(canvas)
                        }
                        canvas.drawText(
                            emotions.value!![x][y].emotion,
                            ((leftBounds + rightBounds) / 2),
                            ((topBounds + bottomBounds) / 2),
                            selectedPaint
                        )

                    }
                }
                isChange = true
            }

            if (emotions.value!!.size > fistIndex.first && emotions.value!![fistIndex.first].size > fistIndex.second && !firstStart) _selectedItem.value =
                emotions.value!![fistIndex.first][fistIndex.second]
            firstStart = false
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
                isScrolling = false
            }

            MotionEvent.ACTION_MOVE -> {
                val deltaX = event.x - startX
                val deltaY = event.y - startY

                if (canvasOffsetX - deltaX < -selected.first ||
                    canvasOffsetY - deltaY < -selected.second ||
                    canvasOffsetY - deltaY > maxSizeOffset.second ||
                    canvasOffsetX - deltaX > maxSizeOffset.first
                ) return true

                if (abs(deltaX) > touchSlop || abs(deltaY) > touchSlop) {
                    isScrolling = true
                    canvasOffsetX -= deltaX
                    canvasOffsetY -= deltaY
                    startX = event.x
                    startY = event.y
                    invalidate()
                }

            }

            MotionEvent.ACTION_UP -> {
                if (!isScrolling) {
                    handleTap(event.x, event.y)
                }
            }
        }
        return true
    }

    private fun handleTap(x: Float, y: Float) {
        val centerGrid = displayMetrics.widthPixels / 2f to displayMetrics.heightPixels / 2f
        val deltaX = centerGrid.first - x
        val deltaY = centerGrid.second - y

        if (canvasOffsetX - deltaX < -selected.first ||
            canvasOffsetY - deltaY < -selected.second ||
            canvasOffsetY - deltaY > maxSizeOffset.second ||
            canvasOffsetX - deltaX > maxSizeOffset.first
        ) return

        canvasOffsetX -= deltaX
        canvasOffsetY -= deltaY
        invalidate()

    }

    private fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }
}