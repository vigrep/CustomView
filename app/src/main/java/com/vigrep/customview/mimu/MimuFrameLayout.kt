package com.vigrep.customview.mimu

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout

class MimuFrameLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    var isAnimating = false
    var downAnimationSet: AnimatorSet? = null
    var upAnimationSet: AnimatorSet? = null
    var shadowPaint: Paint? = null
    var shadowRect: RectF? = null
    val shadeAlpha = 0xFF000000;//背景阴影透明度
    var colorBg:Int? = 0
    private var touchProgress: Float = 1.0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        setWillNotDraw(false)
        shadowPaint = Paint()
        shadowPaint?.style = Paint.Style.FILL
        shadowPaint?.color = Color.BLACK
        shadowPaint?.setShadowLayer(20f, 0f, 0f, Color.BLACK)
        shadowRect = RectF(20f, 20f, width.toFloat() - 20f, height.toFloat() - 20f)

        if (background is ColorDrawable) {
            colorBg = (background as ColorDrawable).color
            shadowPaint?.color = colorBg!!
        }

        background = null
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = View.getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        var height = View.getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        shadowRect?.set(25f, 25f, width - 25.0f, height - 25.0f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var color = (colorBg!! and 0x00FFFFFF) or shadeAlpha.toInt()
        shadowPaint?.setShadowLayer(25f * touchProgress, 1f, 1f, color)
        canvas?.drawRoundRect(shadowRect!!, 1f, 1f, shadowPaint!!)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> executePressAnimator()
            MotionEvent.ACTION_UP -> executeUpAnimator()
            else -> {}
        }
        return true;
    }

    private fun executePressAnimator() {
        if (isAnimating)
            return
        isAnimating = true
        val scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 1f, 0.98f)
        val scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 1f, 0.98f)
        val touchProgressAnimator = ObjectAnimator.ofFloat(this, "touchProgress", 1f, 0.2f);

        downAnimationSet = AnimatorSet()
        downAnimationSet?.playTogether(scaleXAnimator, scaleYAnimator, touchProgressAnimator)
        downAnimationSet?.interpolator = DecelerateInterpolator()
//        downAnimationSet?.duration = 300
        downAnimationSet?.addListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                isAnimating = false
            }

            override fun onAnimationCancel(animation: Animator?) {
                isAnimating = false
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
        downAnimationSet?.start()
    }

    private fun executeUpAnimator() {
        if (downAnimationSet?.isRunning!!)
            downAnimationSet?.cancel()

        val scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", scaleX, 1f)
        val scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", scaleY, 1f)
        val touchProgressAnimator = ObjectAnimator.ofFloat(this, "touchProgress", touchProgress, 1f);

        upAnimationSet = AnimatorSet()
        upAnimationSet?.playTogether(scaleXAnimator, scaleYAnimator, touchProgressAnimator)
        upAnimationSet?.interpolator = DecelerateInterpolator()
//        upAnimationSet?.duration = 300
        upAnimationSet?.addListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
//                isAnimating = false
            }

            override fun onAnimationCancel(animation: Animator?) {
//                isAnimating = false
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
        upAnimationSet?.start()
    }
}