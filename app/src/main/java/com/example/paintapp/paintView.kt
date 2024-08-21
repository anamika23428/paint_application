package com.example.paintapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.paintapp.MainActivity.Companion.paintbrush
import com.example.paintapp.MainActivity.Companion.path

class paintView : View {

    var params: ViewGroup.LayoutParams? = null // responsible for height and width of the canvas wrt parent layout

    companion object {
        var pathlist = ArrayList<Path>()
        var colorlist = ArrayList<Int>()
        var sizelist = ArrayList<Float>()
        var currentbrush = Color.BLACK
        var brushsize = 8f
    }

    constructor(context: Context) : this(context, null) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init()
    }

    // Initialize the paint brush
    private fun init() {
        paintbrush.isAntiAlias = true // making lines smooth
        paintbrush.color = currentbrush
        paintbrush.style = Paint.Style.STROKE
        paintbrush.strokeJoin = Paint.Join.ROUND // make the ending of path round
        paintbrush.strokeWidth = brushsize

        params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    // Register the movements of the finger how the user is drawing on the screen
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                path = Path()
                x?.let { y?.let { it1 -> path.moveTo(it, it1) } }
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                x?.let { y?.let { it1 -> path.lineTo(it, it1) } }

            }

            MotionEvent.ACTION_UP -> {
                pathlist.add(path)
                colorlist.add(currentbrush)
                sizelist.add(brushsize)
            }

            else -> return false
        }
        postInvalidate()
        return false;
    }

    override fun onDraw(canvas: Canvas) {
        for (i in pathlist.indices) {
            paintbrush.color = colorlist[i]
            paintbrush.strokeWidth = sizelist[i]
            canvas.drawPath(pathlist[i], paintbrush)
            invalidate()
        }
        // Draw the current path in progress
        paintbrush.color = currentbrush
        paintbrush.strokeWidth = brushsize
        canvas.drawPath(path, paintbrush)
    }
}
