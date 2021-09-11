package com.vigrep.customview.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawPointView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    val paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.strokeWidth = 20f
        paint.strokeCap = Paint.Cap.ROUND

        canvas.drawPoint(40f, 10f, paint);

        paint.strokeCap = Paint.Cap.SQUARE

        canvas.drawPoint(80f, 10f, paint);

        paint.strokeCap = Paint.Cap.BUTT

        canvas.drawPoint(120f, 10f, paint);
    }
}