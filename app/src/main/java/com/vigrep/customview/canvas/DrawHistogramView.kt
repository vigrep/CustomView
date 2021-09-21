package com.vigrep.customview.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

data class HistogramItem(var name: String, var value: Float, var color: Int)

class DrawHistogramView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var axisMargin = 100f

    private val paint = Paint()
    private val paintStrokeWidth = 10f
    private var xEndX = 0f
    private var xEndY = 0f
    private var yEndX = 0f
    private var yEndY = 0f

    private var originX = axisMargin
    private var originY = 0f

    private var data: FloatArray = floatArrayOf(320f, 300f, 200f, 90f, 100f, 120f, 320f, 300f, 200f, 90f, 100f, 120f)
    private val items: MutableList<HistogramItem> = ArrayList()
    private var preWidth = 100f
    private var preMargin = 100f

    private var xAxisWidth = 100f
    private var histogramMax = 100f

    init {
        paint.strokeWidth = paintStrokeWidth
        items.add(HistogramItem("华为", 225.6f, Color.GREEN))
        items.add(HistogramItem("小米", 220.4f, Color.GRAY))
        items.add(HistogramItem("OPPO", 218.2f, Color.BLUE))
        items.add(HistogramItem("iPhone", 216.8f, Color.YELLOW))
        items.add(HistogramItem("VIVO", 210f, Color.CYAN))
        items.add(HistogramItem("魅族", 209f, Color.BLACK))
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        originX = axisMargin
        originY = measuredHeight - axisMargin

        xEndX = measuredWidth.toFloat()
        xEndY = originY

        yEndX = originX
        yEndY = 0f

        xAxisWidth = xEndX - originX
        preWidth = xAxisWidth / data.size.toFloat()
        preMargin = preWidth / 4.toFloat()

        paint.color = Color.BLACK
        canvas?.drawLine(originX, originY, xEndX, xEndY, paint)
        canvas?.drawLine(originX, originY, yEndX, yEndY, paint)

        histogramMax = originY - yEndY
        var maxValue = 0f
        for (item in items) {
            maxValue = item.value.coerceAtLeast(maxValue)
        }

        var ratio = if (maxValue > histogramMax) histogramMax/maxValue else 1.0f

        for ((index, item) in items.withIndex()) {
            paint.color = item.color
            canvas?.drawRect(originX + preMargin + index*preWidth, histogramMax - item.value * ratio, originX + preMargin + index*preWidth + preWidth/2.toFloat(), originY - paintStrokeWidth/2, paint)
        }
    }

    fun addItem(item: HistogramItem) {
        items.add(item)
        invalidate()
    }

}