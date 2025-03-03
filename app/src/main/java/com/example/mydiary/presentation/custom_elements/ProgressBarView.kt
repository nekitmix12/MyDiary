package com.example.mydiary.presentation.custom_elements

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.SweepGradient
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import com.example.mydiary.R
import kotlin.math.cos
import kotlin.math.sin

class ProgressBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : View(context, attrs) {

    var rotate = true

    private var ringBackgroundGradient: GradientDrawable? = null
    private var ringBackgroundColor: Int = DEFAULT_BACKGROUND
    private var autoCalculatePart: Boolean = false
    private var sizeArraysMin: Int = Int.MAX_VALUE

    //second param is isPD
    private var ringThickness: Pair<Float, Boolean> = Pair(RING_THICKNESS_DEFAULT, true)

    private var foregroundStrokeCap: Types = Types.SQUARE
    private var strokeWidth: Float = DEFAULT_STROKE_WIDTH
    private var progresses: MutableList<Float> = mutableListOf(PROGRESS_DEFAULT)
    private var startAngel: Float = DEFAULT_START_ANGEL
    private var size = 0

    private var center = 0f

    private var innerRadius = 0f

    private var cx = 0f
    private var cy = 0f
    private var rotationAngle = 0f
    private var thickness =
        if (ringThickness.second) ringThickness.first else center * ringThickness.first

    var stroke: List<Pair<Pair<Int, Int>, Float>> = mutableListOf(/*Pair(
                Pair(
                    ContextCompat.getColor(context, R.color.logbook_gradient_yellow_1),
                    ContextCompat.getColor(context, R.color.logbook_gradient_yellow_2)
                ), 0.5f
            ),*//*Pair(
                Pair(
                    ContextCompat.getColor(context, R.color.logbook_gradient_blue_1),
                    ContextCompat.getColor(context, R.color.logbook_gradient_blue_2)
                ), 0.5f
            ),*//*Pair(
                Pair(
                    ContextCompat.getColor(context, R.color.logbook_gradient_green_1),
                    ContextCompat.getColor(context, R.color.logbook_gradient_green_2)
                ), 0.25f
            ),
            Pair(
                Pair(
                    ContextCompat.getColor(context, R.color.logbook_gradient_red_1),
                    ContextCompat.getColor(context, R.color.logbook_gradient_red_2)
                ), 0.25f
            ),*/
    )

    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = thickness

    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        size = (w - paddingStart - paddingEnd).coerceAtMost(h - paddingTop - paddingBottom)
        center = size / 2f
        thickness = if (ringThickness.second) ringThickness.first else center * ringThickness.first
        innerRadius = center - thickness / 2f

        cx = w / 2f
        cy = h / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        createBackgroundProgressBar(canvas)
        createStrokeProgressBar(canvas)
    }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ProgressBarView)

            setBackground(typedArray)
            setAutoCalculate(typedArray)
            setThickness(typedArray)
            setForegroundStrokeCap(typedArray)
            setStartAngle(typedArray)
            setProgresses(typedArray)
            setStrokeWidth(typedArray)
            typedArray.recycle()
            startAnimation()

        }
    }

    private fun setAutoCalculate(typedArray: TypedArray) {
        autoCalculatePart = typedArray.getBoolean(R.styleable.ProgressBarView_autoCalculate, false)
    }

    private fun setBackground(typedArray: TypedArray) {
        if (typedArray.hasValue(R.styleable.ProgressBarView_ringBackground)) {
            val resId = typedArray.getResourceId(R.styleable.ProgressBarView_ringBackground, 0)
            if (resId != 0) {
                try {
                    ringBackgroundColor = ContextCompat.getColor(context, resId)
                } catch (e: Exception) {
                    val drawable = ContextCompat.getDrawable(context, resId)
                    if (drawable is GradientDrawable) {
                        ringBackgroundGradient = drawable
                    } else {
                        throw IllegalArgumentException("Атрибут ringBackground должен быть цветом или GradientDrawable")
                    }
                }
            }
        }
    }


    private fun setForegroundStrokeCap(typedArray: TypedArray) {
        val typeIndex = typedArray.getInt(R.styleable.ProgressBarView_foregroundStrokeCap, 0)
        foregroundStrokeCap = Types.entries.toTypedArray()[typeIndex]
    }

    private fun setThickness(typedArray: TypedArray) {
        RING_THICKNESS_DEFAULT
        val tv: TypedValue? = typedArray.peekValue(R.styleable.ProgressBarView_ringThickness)
        if (tv != null) {
            ringThickness = when (tv.type) {
                //dp
                TypedValue.TYPE_DIMENSION -> {
                    Pair(
                        typedArray.getDimension(
                            R.styleable.ProgressBarView_ringThickness, RING_THICKNESS_DEFAULT
                        ), true
                    )
                }
                //%
                TypedValue.TYPE_FRACTION -> {
                    Pair(
                        typedArray.getFraction(
                            R.styleable.ProgressBarView_ringThickness, 1, 1, RING_THICKNESS_DEFAULT
                        ), false
                    )
                }
                //float
                else -> {
                    val percent = typedArray.getFloat(R.styleable.ProgressBarView_ringThickness, 0f)
                    Pair(percent, false)
                }
            }
        } else {
            ringThickness = Pair(RING_THICKNESS_DEFAULT, true)
        }


    }

    @SuppressLint("Recycle")
    private fun setProgresses(typedArray: TypedArray) {
        val resId = typedArray.getResourceId(R.styleable.ProgressBarView_progress, -1)
        if (resId != -1) {
            val ta = resources.obtainTypedArray(resId)
            sizeArraysMin = ta.length().coerceAtMost(sizeArraysMin)
            for (index in 0 until ta.length()) {
                val tv = ta.peekValue(index)
                if (tv != null) {
                    Log.d("progress_bar_view", "tv.type: ${tv.type}")
                    Log.d("progress_bar_view", "index: $index")
                    if (tv.type != TypedValue.TYPE_FLOAT) throw IllegalArgumentException("invalid array float")
                    progresses.add(ta.getFloat(index, 0f))
                }
            }

            if ((progresses.sum() > 1f || progresses.sum() < 0f) && autoCalculatePart) throw IllegalArgumentException(
                "invalid array float, not in [0,1]"
            )

            if (autoCalculatePart) {
                val sum = progresses.sum()
                for (part in progresses.indices) {
                    progresses[part] = progresses[part] / sum
                }
            }
        } else {
            sizeArraysMin = 1
        }


    }

    private fun setStartAngle(typedArray: TypedArray) {
        startAngel = typedArray.getFloat(R.styleable.ProgressBarView_startAngle, 0f) % 360
    }

    private fun setStrokeWidth(typedArray: TypedArray) {
        val widthRaw = typedArray.getInt(R.styleable.ProgressBarView_strokeWidth, -1)
        strokeWidth = if (widthRaw < 0) 0f else (if (widthRaw > 1f) 1f else widthRaw) as Float
    }

    private fun createBackgroundProgressBar(canvas: Canvas) {

        val center = size / 2f

        val thickness =
            if (ringThickness.second) ringThickness.first else center * ringThickness.first


        val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            if (ringBackgroundGradient == null) color = ringBackgroundColor
            else ringBackgroundGradient?.let { color = ringBackgroundColor }
        }


        backgroundPaint.strokeWidth = thickness
        val innerRadius = center - thickness / 2f

        canvas.drawCircle(cx, cy, innerRadius, backgroundPaint)
    }


    private fun startAnimation() {
        ValueAnimator.ofFloat(0f, 360f).apply {
            duration = 10000L
            repeatMode = ValueAnimator.RESTART
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                if (rotate) {
                    rotationAngle = it.animatedValue as Float
                    startAngel = -90f + rotationAngle
                    invalidate()
                }
            }
            start()
        }
    }

    private fun createStrokeProgressBar(canvas: Canvas) {
        if (stroke.isNotEmpty()) {
            val rect = RectF(cx - innerRadius, cy - innerRadius, cx + innerRadius, cy + innerRadius)
            strokePaint.strokeWidth =
                if (ringThickness.second) ringThickness.first else center * ringThickness.first
            startAngel = -90f


            val colors = mutableListOf<Int>()
            val positions = mutableListOf<Float>()
            var accumulated = 0f

            for ((colorPair, part) in stroke) {
                colors.add(colorPair.first)
                positions.add(accumulated)

                accumulated += part

                colors.add(colorPair.second)
                positions.add(accumulated)
            }


            val shader = SweepGradient(cx, cy, colors.toIntArray(), positions.toFloatArray())
            val matrix = Matrix()
            matrix.preRotate(rotationAngle + startAngel, cx, cy)
            shader.setLocalMatrix(matrix)

            strokePaint.shader = shader
            var currentStartAngle = startAngel + rotationAngle

            for ((_, part) in stroke) {
                val partOfInDegree = 360 * part
                canvas.drawArc(rect, currentStartAngle, partOfInDegree, false, strokePaint)
                currentStartAngle += partOfInDegree
            }

            if (stroke.size == 1) {
                currentStartAngle = startAngel + rotationAngle
                for ((colorPair, part) in stroke) {
                    val partOfInDegree = 360 * part

                    val startAngleRad = Math.toRadians(currentStartAngle.toDouble())
                    val endAngleRad =
                        Math.toRadians((currentStartAngle + partOfInDegree).toDouble())

                    val startX = cx + innerRadius * cos(startAngleRad).toFloat()
                    val startY = cy + innerRadius * sin(startAngleRad).toFloat()
                    val endX = cx + innerRadius * cos(endAngleRad).toFloat()
                    val endY = cy + innerRadius * sin(endAngleRad).toFloat()

                    val endpointPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        style = Paint.Style.FILL
                    }

                    endpointPaint.color = colorPair.first
                    canvas.drawCircle(startX, startY, strokePaint.strokeWidth / 2, endpointPaint)

                    endpointPaint.color = colorPair.second
                    canvas.drawCircle(endX, endY, strokePaint.strokeWidth / 2, endpointPaint)

                    currentStartAngle += partOfInDegree
                }
            }
        }
    }


    companion object {
        val DEFAULT_BACKGROUND = Color.parseColor("#1A1A1A")

        val DEFAULT_STROKE = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(Color.parseColor("#333333"), Color.parseColor("#333333"))
        )
        const val DEFAULT_STROKE_WIDTH = 0.5f
        const val PROGRESS_DEFAULT = 0.5f

        enum class Types {
            BUTT, ROUND, SQUARE
        }

        const val DEFAULT_START_ANGEL = 0f
        const val RING_THICKNESS_DEFAULT = 4f
    }
}