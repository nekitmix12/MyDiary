package com.example.mydiary.presentation.custom_elements

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
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

    private var ringBackgroundGradient: GradientDrawable? = null
    private var ringBackgroundColor: Int = DEFAULT_BACKGROUND
    private var autoCalculatePart: Boolean = false
    private var sizeArraysMin: Int = Int.MAX_VALUE

    //second param is isPD
    private var ringThickness: Pair<Float, Boolean> = Pair(RING_THICKNESS_DEFAULT, true)
    private var ringStrokeGradients: MutableList<Pair<GradientDrawable, Int>> = mutableListOf()
    private var ringStrokeColors: MutableList<Pair<Int, Int>> = mutableListOf()

    private var foregroundStrokeCap: Types = Types.SQUARE
    private var strokeWidth: Float = DEFAULT_STROKE_WIDTH
    private var progresses: MutableList<Float> = mutableListOf(PROGRESS_DEFAULT)
    private var startAngel: Float = DEFAULT_START_ANGEL

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        createBackgroundProgressBar(canvas)
        createStrokeProgressBar(canvas)
    }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ProgressBarView)

            setBackground(typedArray)
            setStroke(typedArray)
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

    @SuppressLint("Recycle")
    private fun setStroke(typedArray: TypedArray) {
        val resId = typedArray.getResourceId(R.styleable.ProgressBarView_ringStroke, 0)
        if (resId != 0) {
            val ta = resources.obtainTypedArray(resId)
            sizeArraysMin = ta.length().coerceAtMost(sizeArraysMin)
            for (index in 0 until ta.length()) {
                val tv = ta.peekValue(index)
                if (tv != null) {
                    val itemResId = ta.getResourceId(index, 0)
                    if (itemResId != 0) {
                        try {
                            ringStrokeColors.add(
                                Pair(
                                    ContextCompat.getColor(context, itemResId), index
                                )
                            )
                        } catch (e: Exception) {
                            val drawable = ta.getDrawable(index)
                            if (drawable is GradientDrawable) {
                                ringStrokeGradients.add(Pair(drawable, index))
                            }
                        }
                    } else {
                        try {
                            val colorString = ta.getString(index)
                            if (!colorString.isNullOrEmpty()) {
                                ringStrokeColors.add(Pair(Color.parseColor(colorString), index))
                            }
                        } catch (e: Exception) {
                            Log.d(
                                "progress_bar_view", "Элемент с индексом $index не является цветом"
                            )
                        }
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
        val size =
            (width - paddingStart - paddingEnd).coerceAtMost(height - paddingTop - paddingBottom)

        val cx = width / 2f
        val cy = height / 2f
        val center = size / 2f

        val thickness =
            if (ringThickness.second) ringThickness.first else center * ringThickness.first


        val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            if (ringBackgroundGradient == null) color = ringBackgroundColor
            else ringBackgroundGradient?.let { shader = convertGradientToShader(it) }
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
                startAngel = it.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    private fun createStrokeProgressBar(canvas: Canvas) {
        val size =
            (width - paddingStart - paddingEnd).coerceAtMost(height - paddingTop - paddingBottom)

        val cx = width / 2f
        val cy = height / 2f
        val center = size / 2f

        val thickness =
            if (ringThickness.second) ringThickness.first else center * ringThickness.first

        val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = thickness

        }
        val innerRadius = center - thickness / 2f


        val rect = RectF(
            cx - innerRadius, cy - innerRadius, cx + innerRadius, cy + innerRadius
        )
        val sweepAngle = 90f

        val startColor = ContextCompat.getColor(context, R.color.logbook_gradient_yellow_1)
        val endColor = ContextCompat.getColor(context, R.color.logbook_gradient_yellow_2)
        val shader =
            SweepGradient(cx, cy, intArrayOf(startColor, endColor), floatArrayOf(0f, 0.25f))

        val matrix = Matrix()
        matrix.preRotate(startAngel - 90f, cx, cy)
        shader.setLocalMatrix(matrix)

        /*when(foregroundStrokeCap){
            Types.BUTT->{ strokePaint.strokeCap = Paint.Cap.BUTT}
            Types.ROUND->{ strokePaint.strokeCap = Paint.Cap.ROUND}
            Types.SQUARE->{ strokePaint.strokeCap = Paint.Cap.SQUARE}
        }*/
        strokePaint.shader = shader


        canvas.drawArc(rect, startAngel - 90f, sweepAngle, false, strokePaint)
        val startAngleRad = Math.toRadians((startAngel - 90f).toDouble())
        val endAngleRad = Math.toRadians((startAngel - 90f + sweepAngle).toDouble())

        val startX = cx + innerRadius * cos(startAngleRad).toFloat()
        val startY = cy + innerRadius * sin(startAngleRad).toFloat()
        val endX = cx + innerRadius * cos(endAngleRad).toFloat()
        val endY = cy + innerRadius * sin(endAngleRad).toFloat()

        val endpointPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
        }

        endpointPaint.color = startColor
        canvas.drawCircle(startX, startY, thickness / 2f, endpointPaint)

        endpointPaint.color = endColor
        canvas.drawCircle(endX, endY, thickness / 2f, endpointPaint)


        /*        var iColor = 0
                var iGradient = 0
                Log.d("progress_bar_view", "progresses: $progresses")

                for (pos in 0 until sizeArraysMin) {
                    if (iColor < ringStrokeColors.size && ringStrokeColors[iColor].second == pos) {
                        val color = ringStrokeColors[iColor]
                        strokePaint.color = color.first
                        strokePaint.shader = null
                        canvas.drawArc(rect, startAngleLocal, progresses[pos] * 360, false, strokePaint)
                        startAngleLocal += progresses[pos] * 360
                        iColor++
                    }
                    if (iGradient < ringStrokeGradients.size && ringStrokeGradients[iGradient].second == pos) {
                        Log.d("progress_bar_view", "pos: $pos")
                        val gradient = ringStrokeGradients[iGradient]
                        strokePaint.color = Color.RED
                        Log.d("progress_bar_view", "progresses: ${progresses[pos]}")
                        strokePaint.shader = convertGradientToShader(gradient.first) convertGradientToShader(ContextCompat.getDrawable(context,R.drawable.logbook_gradient_red) as GradientDrawable)

                        canvas.drawArc(rect, startAngleLocal, progresses[pos] * 360, false, strokePaint)
                        startAngleLocal += progresses[pos] * 360
                        iGradient++
                    }
                    Log.d("progress_bar_view", "startAngleLocal: $startAngleLocal")

                }*/
    }

    private fun convertGradientToShader(gradientDrawable: GradientDrawable): LinearGradient {
        val colors = gradientDrawable.colors ?: throw Exception("Invalid Gradient")
        val (x0, y0, x1, y1) = when (gradientDrawable.orientation) {
            GradientDrawable.Orientation.LEFT_RIGHT -> floatArrayOf(0f, 0f, width.toFloat(), 0f)
            GradientDrawable.Orientation.TOP_BOTTOM -> floatArrayOf(0f, 0f, 0f, height.toFloat())
            GradientDrawable.Orientation.BL_TR -> floatArrayOf(
                width.toFloat(), 0f, 0f, height.toFloat()
            )

            GradientDrawable.Orientation.BR_TL -> floatArrayOf(
                0f, height.toFloat(), width.toFloat(), 0f
            )

            GradientDrawable.Orientation.TL_BR -> floatArrayOf(
                0f, 0f, width.toFloat(), height.toFloat()
            )

            GradientDrawable.Orientation.TR_BL -> floatArrayOf(
                width.toFloat(), 0f, 0f, height.toFloat()
            )

            else -> floatArrayOf(0f, 0f, width.toFloat(), 0f)
        }

        return LinearGradient(x0, y0, x1, y1, colors, null, Shader.TileMode.CLAMP)
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