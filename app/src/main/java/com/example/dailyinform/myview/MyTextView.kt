package com.example.dailyinform.myview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView
import com.example.dailyinform.R

class MyTextView : TextView {
    var isSelect = false
    private var selectTextColor = 0
    private var unSelectTextColor = 0

    constructor(context: Context) : super(context) {}

    constructor(mContext: Context, attributeSet: AttributeSet?) : super(mContext, attributeSet) {
        if (attributeSet == null) {
            return
        }
        val array = context.obtainStyledAttributes(attributeSet, R.styleable.MyTextView)
        initAttr(array)

    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
    }

    private fun initAttr(array: TypedArray) {
        isSelect = array.getBoolean(R.styleable.MyTextView_isSelect, false)
        selectTextColor = array.getColor(R.styleable.MyTextView_selectTextColor, Color.parseColor("#FAA96A"))
        unSelectTextColor = array.getColor(R.styleable.MyTextView_unSelectTextColor, Color.parseColor("#888888"))
        array.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }
        if (isSelect) {
            super.setTextColor(selectTextColor)
        } else {
            super.setTextColor(unSelectTextColor)
        }
    }
}