package com.uestc.base.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


/**
 * Created by PengFeifei on 2018/10/27.
 */

val String?.color: Int
    get() {
        if (this.isNullOrEmpty()) {
            return Color.TRANSPARENT
        }
        return Color.parseColor(this)
    }

val String?.int: Int
    get() {
        if (this.isNullOrEmpty()) {
            return 0
        }
        return this!!.toInt()
    }

fun View.click(block: () -> Unit) {
    this.setOnClickListener {
        block()
    }
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}


fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun Context.inflate(res: Int, container: ViewGroup?, attach: Boolean = false): View {
    return LayoutInflater.from(this).inflate(res, container, attach)
}

fun List<*>?.length():Int {
    if (this.isNullOrEmpty()) {
        return 0
    }
    return size
}

fun TextView.visibleText(content: String?, needGone: Boolean = true) {
    if (content.isNullOrBlank()) {
        visibility = if (needGone) View.GONE else View.INVISIBLE
        return
    }
    text = content
    visibility = View.VISIBLE
}

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
