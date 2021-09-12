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

    private var data: FloatArray = floatArrayOf(100f, 120f, 60f)
    private val preWidth = 100f
    private val preMargin = 100f

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

        canvas?.drawLine(originX, originY, xEndX, xEndY, paint)
        canvas?.drawLine(originX, originY, yEndX, yEndY, paint)

        paint.color = Color.GREEN

        for ((index, value) in data.withIndex()) {
            canvas?.drawRect(originX + (index+1)*preMargin + index*preWidth, value, originX + (index+1)*preMargin + index*preWidth + preWidth, originY - paintStrokeWidth/2, paint)
        }
    }

}