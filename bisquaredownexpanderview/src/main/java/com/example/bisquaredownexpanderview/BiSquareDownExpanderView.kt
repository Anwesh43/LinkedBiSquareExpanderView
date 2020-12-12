package com.example.bisquaredownexpanderview

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Color
import android.graphics.Canvas
import android.graphics.RectF

val parts : Int = 5
val scGap : Float = 0.02f / parts
val sizeFactor : Float = 5.8f
val backColor : Int = Color.parseColor("#BDBDBD")
val colors : Array<Int> = arrayOf(
    "#F44336",
    "#2196F3",
    "#4CAF50",
    "#FF9800",
    "#673AB7"
).map {
    Color.parseColor(it)
}.toTypedArray()
val delay : Long = 20

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawBiSquareDownExpander(scale : Float, w : Float, h : Float, paint : Paint) {
    val sf : Float = scale.sinify()
    val sfLast : Float = sf.divideScale(3, parts)
    val size : Float = Math.min(w, h) / sizeFactor
    val hUp : Float = 2 * size * sf.divideScale(0, parts) + (h - 2 * size) * sfLast
    val x : Float = (w / 2 - size)  * sf.divideScale(2, parts) - size
    val y : Float = (h - 2 * size) * (sf.divideScale(1, parts) - sfLast)
    save()
    translate(w / 2, y)
    drawRect(RectF(x, 0f, x + 2 * size, hUp), paint)
    restore()
}

fun Canvas.drawBSDENode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    drawBiSquareDownExpander(scale, w, h, paint)
}

class BiSquareDownExpanderView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            scale += scGap * dir
            if (Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                cb(prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f - 2 * prevScale
                cb()
            }
        }
    }
}
