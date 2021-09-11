package com.vigrep.customview.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class DrawPathView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()
    private val path = Path()
    private val rectF1 = RectF()
    private val rectF2 = RectF()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val y1 = measuredHeight.toFloat() * 100 / 171
        val y2 = measuredHeight.toFloat() - y1

        rectF1.set(measuredWidth/2.toFloat() - y1, 0f, measuredWidth/2.toFloat(), y1)
        rectF2.set(measuredWidth/2.toFloat(), 0f, measuredWidth/2.toFloat() + y1, y1)

        path.addArc(rectF1, -225f, 225f)
        path.arcTo(rectF2, -180f, 225f)
        path.lineTo(measuredWidth/2.toFloat(), measuredHeight.toFloat())//y: 342
        path.close()

        canvas?.drawPath(path, paint)
    }
}