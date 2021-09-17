package com.vigrep.customview.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class DrawPieChartView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()
    private val data = floatArrayOf(100f, 100f, 100f, 100f)
    private val colors = intArrayOf(Color.BLUE, Color.RED, Color.BLACK, Color.GREEN)
    private var startAngle = -180f
    private val rectF = RectF()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val left = (measuredWidth - measuredHeight)/2.toFloat()
        rectF.set(left, 0f, left + measuredHeight.toFloat(), measuredHeight.toFloat())

        var total = 0f
        for(value in data) {
            total += value
        }
        for((index, value) in data.withIndex()) {
            var angle = value / total * 360

            paint.color = colors[index]

            canvas?.drawArc(rectF, startAngle, angle, true, paint)
            startAngle += angle
        }
    }
}