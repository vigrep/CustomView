package com.vigrep.customview.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

class DrawOvalView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()
    private val rectF = RectF()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        rectF.set(10f, 0f, measuredWidth/2.toFloat() - 20f, measuredHeight.toFloat())

        canvas.drawOval(rectF, paint)

        paint.style = Paint.Style.STROKE

        canvas.drawOval(measuredWidth/2.toFloat() + 10f, 0f, measuredWidth - 20f, measuredHeight.toFloat(), paint)
    }
}