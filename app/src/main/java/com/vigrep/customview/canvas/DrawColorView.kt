package com.vigrep.customview.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawColorView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint: Paint = Paint()

    init {
        paint.color = Color.BLACK
        paint.textSize = 50f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.YELLOW);
        canvas.drawText("drawColor练习", measuredWidth / 2.toFloat(), measuredHeight / 2.toFloat(), paint)
    }

}