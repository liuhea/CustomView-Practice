package com.liuhe.customviewpractice

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.liuhe.kotlinutilslib.getScreenHeight
import com.liuhe.kotlinutilslib.getScreenWidth
import com.liuhe.kotlinutilslib.log
import com.liuhe.kotlinutilslib.toast


/**
 * 自定义View 默认四个构造函数
 *
 *
 * Created by liuhe on 20/03/2018.
 *
 * java类的加载过程 静态代码块及静态成员->成员变量->构造函数
 * kotlin 成员变量->主构造函数和init
 */
class CanvasView(context: Context) : View(context) {

    val txt = "liuhe's blog"
    private lateinit var redPaint: Paint
    private lateinit var bluePaint: Paint
    private val facePoint = PointF(getScreenWidth(context) / 2, getScreenHeight(context) / 2)
    private val pointLeftEyeStart = PointF(420f, 840f)
    private val pointRightEyeStart = PointF(620f, 840f)
    private val pointLeftEyeStop = PointF(460f, 840f)
    private val pointRightEyeStop = PointF(660f, 840f)
    val faceRadius = 200f

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : this(context)

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {
        init()
    }

    private fun init() {
        redPaint = Paint()
        bluePaint = Paint()
        redPaint.apply {
            color = Color.RED
            style = Paint.Style.STROKE
            // 抗锯齿（毛刺），开启后，占用内存
            isAntiAlias = true
        }
        bluePaint.apply {
            color = Color.BLUE
            style = Paint.Style.FILL_AND_STROKE
            // 抗锯齿（毛刺），开启后，占用内存
            isAntiAlias = true
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        "onMeasure".log()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        "onLayout".log()
    }

    /**
     * 绘制阶段调用此方法
     * @param canvas 画布
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        "onDraw".log()

        /*
        画圆
        cx,cy圆心的坐标
        radius圆的半径
        paint 画笔
         */
        canvas?.drawCircle(facePoint.x, facePoint.y, faceRadius, redPaint)

        /*
        画线
        startX,startY
        stopX,stopY
        paint
         */
        canvas?.drawLine(pointLeftEyeStart.x, pointLeftEyeStart.y, pointLeftEyeStop.x, pointLeftEyeStop.y, redPaint)

        /*
        RectF oval :　用于确定圆弧形状与尺寸的椭圆边界（即椭圆外切矩形）
       float startAngle  开始角度（以时钟3点的方向为0°，顺时针为正方向）
       float sweepAngle 扫过角度（以时钟3点的方向为0°，顺时针为正方向）
       boolean useCenter 是否包含圆心
       Paint paint绘制圆弧的画笔
         */
        val rectF = RectF(440f, 1060f, 640f, 1160f)
        canvas?.drawRect(rectF, redPaint)
        canvas?.drawArc(rectF, 30f, 120f, true, bluePaint)

        drawText(canvas)

    }

    /**
     * String text 文字
     * float x, 所画图形的左上角x轴
     * float y, 基线的位置
     * Paint paint 画笔
     * public void drawText(@NonNull String text, float x, float y, @NonNull Paint paint) {
     */
    private fun drawText(canvas: Canvas?) {
        val paint = Paint().apply {
            color = Color.RED
            //设置防抖动
            isDither = true
            //抗锯齿
            isAntiAlias = true
            strokeWidth = 3f
            textSize = 100f
        }

        //测量文本高
        val textBounds = Rect()
        paint.getTextBounds(txt, 0, txt.length, textBounds)
        val textH = textBounds.height()

        // 基线
        var baselineY = 100
        canvas?.drawLine(0f, baselineY.toFloat(), 600f, baselineY.toFloat(), bluePaint)
        paint.style = Paint.Style.STROKE
        canvas?.drawText(txt, 10f, baselineY.toFloat(), paint)
        paint.style = Paint.Style.FILL
        canvas?.drawText(txt, 10f, (100 + textH * 1 + 50).toFloat(), paint)
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas?.drawText(txt, 10f, (100 + textH * 2 + 100).toFloat(), paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        "event?.x=${event?.x} -- event.rawX=${event?.rawX}".log()
        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(event)
    }

}