package com.vigrep.customview.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class DrawRectView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint: Paint = Paint()
    private val rect1 = Rect()
    private val rect2 = RectF()
    private val rect3 = RectF()
    private val rect4 = RectF()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        rect1.set(0,0,measuredWidth/4,measuredHeight/2);
        rect2.set(rect1.right.toFloat() + 10f,0f,measuredWidth/2.toFloat(),measuredHeight/2.toFloat());

        canvas.drawRect(rect1, paint)
        canvas.drawRect(rect2, paint)

        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        rect2.left = rect2.right + 10f
        rect2.right = measuredWidth/4.toFloat()*3
        canvas.drawRoundRect(rect2, 20f, 20f, paint)

        rect3.set(rect2.right + 10f, 0f, measuredWidth - 10f, measuredHeight/2.toFloat())
        rect4.set(rect2.right + 20f, 10f, measuredWidth - 20f, measuredHeight/2.toFloat() - 10f)

//        canvas.drawDoubleRoundRect(rect3, 10f, 10f, rect4, 10f, 10f, paint)

    }
}