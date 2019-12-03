package com.uestc.medicine.adapter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uestc.medicine.net.DishShopEntity
import com.uestc.medicine.util.px


/**
 * Created by PengFeifei on 2019-11-29.
 */
class StickyItemDecoration : RecyclerView.ItemDecoration() {

    companion object {
        private val HEIGHT = 29.px
    }

    private val textPaint: Paint by lazy {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.parseColor("#0000ff")
        paint.textSize = 30f
        return@lazy paint
    }
    private val mPaint: Paint by lazy {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.parseColor("#00ff00")
        return@lazy paint
    }


//
//    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//        val isHeader = view.tag != null && (view.tag as DishShopEntity).isHeader
//        if (isHeader && parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount - 1) {
//            outRect.set(0, HEIGHT, 0, 0)
//        }
//    }


//    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        val childCount = parent.childCount
//        for (i in 0 until childCount) {
//            val view = parent.getChildAt(i)
//            val position = parent.getChildAdapterPosition(view)
//            val isHeader = view.tag != null && (view.tag as DishShopEntity).isHeader
//            if (position != RecyclerView.NO_POSITION && isHeader) {
//                drawHeader(c, parent, view, position)
//            }
//        }
//    }
//
//    private fun drawHeader(c: Canvas, parent: RecyclerView, view: View, position: Int) {
//        val params = view.layoutParams as RecyclerView.LayoutParams
//        val left = parent.paddingLeft
//        val right = parent.width - parent.paddingRight
//        val bottoom = view.top - params.topMargin - ViewCompat.getTranslationY(view).roundToInt()
//        val top = (bottoom - HEIGHT)
//        // 计算文字居中时候的基线
//        val targetRect = Rect(left, top, right, bottoom)
//        val fontMetrics = textPaint.fontMetricsInt
//        val baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2
//        c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottoom.toFloat(), mPaint)
//        c.drawText("------", left.toFloat(), baseline.toFloat(), textPaint)
//    }


    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val layoutManager = parent.layoutManager as LinearLayoutManager;
        val item01 = layoutManager.getChildAt(0)
        val item02 = layoutManager.getChildAt(1)
        if (item01 != null && item02 != null) {
            val section1 = item01.tag as DishShopEntity
            val section2 = item02.tag as DishShopEntity

            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight
            var bottoom = HEIGHT
            var top = 0
            // 判断是否达到临界点
            // (第一个可见item是每组的最后一个,第二个可见tiem是下一组的第一个,并且第一个可见item的底部小于header的高度)
            // 这里直接判断item的底部位置小于header的高度有点欠妥,应该还要考虑paddingtop以及margintop,这里展示不考虑了
            if (section1.isEndOfType && section2.isHeader && item01.bottom <= HEIGHT) {
                bottoom = item01.bottom
                top = (bottoom - HEIGHT)
            }
            // 计算文字居中时候的基线
            val targetRect = Rect(left, top, right, bottoom)
            val fontMetrics = textPaint.fontMetricsInt
            val baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2
            // 背景
            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottoom.toFloat(), mPaint)
            // 文字
            canvas.drawText((item01.tag as DishShopEntity).textOFtype, left.toFloat(), baseline.toFloat(), textPaint)
        }

    }

}