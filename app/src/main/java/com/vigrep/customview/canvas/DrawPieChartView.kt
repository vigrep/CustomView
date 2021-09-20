package com.vigrep.customview.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

data class PieItem(var name: String, var value: Float, var color: Int)

class DrawPieChartView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()
    private val items: MutableList<PieItem> = ArrayList()
    private var startAngle = -180f
    private val rectF = RectF()
    private val itemNameRectF = RectF()
    private var nameHeight = 20f
    private var namePadding = 10f
    private var nameLeftMargin = 30f

    init {
        items.add(PieItem("华为", 25.6f, Color.GREEN))
        items.add(PieItem("小米", 20.4f, Color.GRAY))
        items.add(PieItem("OPPO", 18.2f, Color.BLUE))
        items.add(PieItem("iPhone", 16.8f, Color.YELLOW))
        items.add(PieItem("VIVO", 10f, Color.CYAN))
        items.add(PieItem("魅族", 9f, Color.BLACK))
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val left = (measuredWidth - measuredHeight)/2.toFloat()
        rectF.set(left, 0f, left + measuredHeight.toFloat(), measuredHeight.toFloat())

        nameHeight = measuredHeight/items.size.toFloat()

        var total = 0f
        for(pieItem in items) {
            total += pieItem.value
        }
        var y = 0f
        for(pieItem in items) {
            val angle = pieItem.value / total * 360

            paint.strokeCap = Paint.Cap.BUTT
            paint.color = pieItem.color

            canvas?.drawArc(rectF, startAngle, angle, true, paint)
            startAngle += angle

            paint.strokeCap = Paint.Cap.SQUARE
            itemNameRectF.set(rectF.right + nameLeftMargin, y + namePadding, rectF.right + nameLeftMargin + nameHeight, y + nameHeight - namePadding)
            canvas?.drawRect(itemNameRectF, paint)
            paint.color = Color.BLACK
            paint.textSize = nameHeight - 2 * namePadding
            canvas?.drawText(pieItem.name, itemNameRectF.right + nameLeftMargin, itemNameRectF.bottom, paint)
            y += nameHeight
        }
    }

    fun addPieItem(pieItem: PieItem) {
        items.add(pieItem)
        invalidate()
    }
}