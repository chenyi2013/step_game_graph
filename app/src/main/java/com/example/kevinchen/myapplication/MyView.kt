package com.example.kevinchen.myapplication

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

/**
 * TODO: document your custom view class.
 */
class MyView : View {


    private val bufferRect = Rect()
    private val textPaint = Paint()

    private var bitmap: Bitmap? = null
    var newBitmap: Bitmap? = null
    var lastBitmap:Bitmap? = null


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }


    private fun getFontHeight(paint: Paint, text: String): Int {

        paint.getTextBounds(text, 0, text.length, bufferRect)
        return bufferRect.height()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        textPaint.textSize = ScreenUtil.sp2px(context, 13f)
        val height = ScreenUtil.dip2px(context, 108f)+2 * getFontHeight(textPaint, "123")+ScreenUtil.dip2px(context, 36f)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.step_graph_4_k)

        val bothPadding = ScreenUtil.dip2px(context, 10f)
        val d = ScreenUtil.dip2px(context, 5f)
        val preWidth = (w - bothPadding * 2 - 5 * d) / 6f
        newBitmap = Bitmap.createScaledBitmap(bitmap, preWidth.toInt(), ScreenUtil.dip2px(context, 108f), false)
        lastBitmap = BitmapFactory.decodeResource(resources,R.drawable.step_graph_5_k)

        lastBitmap = Bitmap.createScaledBitmap(lastBitmap, preWidth.toInt(), ScreenUtil.dip2px(context, 108f),false)

    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {

    }


    override fun onDraw(canvas: Canvas) {



        super.onDraw(canvas)

        canvas.save()


        val top = 2 * getFontHeight(textPaint, "123").toFloat()+ScreenUtil.dip2px(context,36f)
        val left = ScreenUtil.dip2px(context,10f)

        val rectF = RectF()
        rectF.set(0f, 0f, width.toFloat(), height - 20f)
        canvas.clipRect(rectF)



        val bothPadding = ScreenUtil.dip2px(context, 10f)
        val d = ScreenUtil.dip2px(context, 5f)
        val preWidth = (width - bothPadding * 2 - 5 * d) / 6f


        for(i in 0..5){

            if(i == 5){
                canvas.drawBitmap(lastBitmap,bothPadding+i*preWidth+i*d,top+40*(5-i),textPaint)

            }else{
                canvas.drawBitmap(newBitmap,bothPadding+i*preWidth+i*d,top+40*(5-i),textPaint)
            }
        }







        canvas.restore()



    }
}
