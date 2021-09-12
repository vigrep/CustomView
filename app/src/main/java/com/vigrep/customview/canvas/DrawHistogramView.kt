package com.vigrep.customview.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawHistogramView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()
    private var yStartX = 100f
    private var yStartY = 0f
    private var yEndX = 100f
    private var yEndY = 0f
    private var xStartX = 0f
    private var xStartY = 0f
    private var xEndX = 0f
    private var xEndY = 0f

    init {
        paint.strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        yEndY = measuredHeight - 60f
        xStartX = 60f
        xStartY = measuredHeight - 100f
        xEndX = measuredWidth.toFloat() - 60f
        xEndY = xStartY

        canvas?.drawLine(yStartX, yStartY, yEndX, yEndY, paint)
        canvas?.drawLine(xStartX, xStartY, xEndX, xEndY, paint)
    }

}