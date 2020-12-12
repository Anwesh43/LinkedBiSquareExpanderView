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

