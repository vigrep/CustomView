package com.vigrep.customview.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class DrawArcView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()
    private val rectF = RectF()

    init {
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        rectF.set(measuredWidth/4.toFloat(), 0f, measuredWidth/4.toFloat()*3, measuredHeight.toFloat())

        canvas.drawArc(rectF, 0f, 180f, true, paint)

        canvas.drawArc(rectF, -100f, 90f, true, paint)

        paint.style = Paint.Style.STROKE
        canvas.drawArc(rectF, 190f, 50f, false, paint)
    }
}