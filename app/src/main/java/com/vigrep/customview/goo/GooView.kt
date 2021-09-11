package com.vigrep.customview.goo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

class GooView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var outlinePaint: Paint = Paint()
    private var dotPaint: Paint = Paint()
    private var centerX: Float = 0f
    private var centerY: Float = 0f

    init {
        outlinePaint.color = Color.RED
        outlinePaint.strokeWidth = 2.0f
        outlinePaint.style = Paint.Style.STROKE

        dotPaint.color = Color.RED
        dotPaint.strokeWidth = 2.0f
        dotPaint.style = Paint.Style.FILL
        setWillNotDraw(false)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        canvas.apply {
            drawCircle(centerX, centerY, measuredWidth/2.0f, outlinePaint)
            if (test) {
                dotPaint.color = Color.BLUE
            } else {
                dotPaint.color = Color.RED
            }
            drawCircle(centerX, centerY, 50f, dotPaint)
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        centerX = measuredWidth / 2.0f;
        centerY = measuredHeight / 2.0f;
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (isInDot(event.x, event.y)) {
                    test()
                } else {
                    test = false
                    invalidate()
                }
            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_UP -> {

            }
        }
        return true
    }

    private fun isInDot(x: Float, y: Float) = x >= centerX - 50.0f && x <= centerX + 50.0f && y >= centerY - 50.0f && y <= centerY + 50.0f

    private var test = false
    private fun test() {
        test = true
        invalidate()
    }

}