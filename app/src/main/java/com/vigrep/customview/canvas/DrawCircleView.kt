package com.vigrep.customview.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawCircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paintFill: Paint = Paint()
    private val paintStroke: Paint = Paint()

    init {
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val radius = (measuredWidth - 50) / 4.toFloat() / 2.toFloat()

        val margin = 10f
        val circleD = 2 * radius

        val x1 = margin + radius;
        val y1 = radius
        canvas.drawCircle(x1, y1, radius, paintFill)

        val x2 = 1 * (circleD + margin) + margin + radius
        val y2 = radius
        paintFill.style = Paint.Style.STROKE
        canvas.drawCircle(x2, y2, radius, paintFill)

        val x3 = 2 * (circleD + margin) + margin + radius
        val y3 = radius
        paintFill.style = Paint.Style.FILL
        paintFill.color = Color.BLUE
        canvas.drawCircle(x3, y3, radius, paintFill)

        val x4 = 3 * (circleD + margin) + margin + radius
        val y4 = radius
        paintFill.style = Paint.Style.STROKE
        paintFill.strokeWidth = 20f
        canvas.drawCircle(x4, y4, radius - 10f, paintFill)
    }
}