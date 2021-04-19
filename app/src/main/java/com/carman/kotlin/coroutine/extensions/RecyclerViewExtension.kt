package com.carman.kotlin.coroutine.extensions

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator


fun RecyclerView.setVerticalTransparentDivider(height: Int) {

    this.addItemDecoration(object : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view)
            outRect.top = if (position == 0) 0 else height
        }
    })
}

fun RecyclerView.setHorizontalTransparentDivider(width: Int) {
    this.addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view)
            outRect.left = if (position == 0) 0 else width
        }
    })
}

/**
 * 设置首页的list的marginBottom，最后一项需要加个很大的marginBottom
 * @param marginBottom 设置0~最后一项的marginBottom
 * @param lastItemMarginBottom 设置最后一项的marginBottom
 */
fun RecyclerView.setHomePartyMarginBottom(marginBottom: Int, lastItemMarginBottom: Int) {
    this.addItemDecoration(object : RecyclerView.ItemDecoration() { // 列表垂直间距
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val itemCount = parent.adapter?.itemCount ?: 0
            if (itemCount == 0) {
                outRect.bottom = 0
            } else {
                val lastItemPosition = itemCount - 1
                when (parent.getChildAdapterPosition(view)) {
                    lastItemPosition -> outRect.bottom = lastItemMarginBottom
                    else -> outRect.bottom = marginBottom
                }
            }
        }
    })
}

/**
 * 打开默认局部刷新动画
 */
fun RecyclerView.openDefaultAnimator() {
    this.itemAnimator?.addDuration = 120
    this.itemAnimator?.changeDuration = 250
    this.itemAnimator?.moveDuration = 250
    this.itemAnimator?.removeDuration = 120
    (this.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = true
}

/**
 * 关闭默认局部刷新动画
 */
fun RecyclerView.closeDefaultAnimator() {
    this.itemAnimator?.addDuration = 0
    this.itemAnimator?.changeDuration = 0
    this.itemAnimator?.moveDuration = 0
    this.itemAnimator?.removeDuration = 0
    (this.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
}