package com.vigrep.customview.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawHistogramView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var axisMargin = 100f

    private val paint = Paint()
    private val paintStrokeWidth = 10f
    private var xEndX = 0f
    private var xEndY = 0f
    private var yEndX = 0f
    private var yEndY = 0f

    private var originX = axisMargin
    private var originY = 0f

    private var data: FloatArray = floatArrayOf(320f, 300f, 200f, 90f, 100f, 120f, 320f, 300f, 200f, 90f, 100f, 120f)
    private var preWidth = 100f
    private var preMargin = 100f

    private var xAxisWidth = 100f
    private var histogramMax = 100f

    init {
        paint.strokeWidth = paintStrokeWidth
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        originX = axisMargin
        originY = measuredHeight - axisMargin

        xEndX = measuredWidth.toFloat()
        xEndY = originY

        yEndX = originX
        yEndY = 0f

        xAxisWidth = xEndX - originX
        preWidth = xAxisWidth / data.size.toFloat()
        preMargin = preWidth / 4.toFloat()

        canvas?.drawLine(originX, originY, xEndX, xEndY, paint)
        canvas?.drawLine(originX, originY, yEndX, yEndY, paint)

        paint.color = Color.GREEN

        histogramMax = originY - yEndY
        var maxValue = 0f
        for (value in data) {
            maxValue = value.coerceAtLeast(maxValue)
        }

        var ratio = if (maxValue > histogramMax) histogramMax/maxValue else 1.0f

        for ((index, value) in data.withIndex()) {
            canvas?.drawRect(originX + preMargin + index*preWidth, histogramMax - value * ratio, originX + preMargin + index*preWidth + preWidth/2.toFloat(), originY - paintStrokeWidth/2, paint)
        }
    }

}