package com.vigrep.customview.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawLineView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()

    init {
        paint.strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawLine(0f, 0f, measuredWidth/3.toFloat(), measuredHeight / 2.toFloat(), paint)

        paint.color = Color.RED
        val f1 = floatArrayOf(0f, 0f, measuredWidth/4.toFloat(), measuredHeight.toFloat(),
            measuredWidth/4.toFloat(), measuredHeight.toFloat(), measuredWidth/2.toFloat(), 0f,
            measuredWidth/2.toFloat(), 0f, measuredWidth/4.toFloat()*3, measuredHeight.toFloat(),
            measuredWidth/4.toFloat()*3, measuredHeight.toFloat(), measuredWidth.toFloat(), 0f
        )
        canvas.drawLines(f1, paint)

        paint.strokeWidth = 5f
        paint.color = Color.WHITE
        canvas.drawLines(f1, 4, 8, paint)
    }
}