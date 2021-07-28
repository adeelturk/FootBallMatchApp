package com.turk.common.extension

import android.graphics.Rect
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


enum class MarginStrategy {
    ONLY_TOP,
    ONLY_RIGHT,
    ONLY_BOTTOM,
    ALL_BUT_TOP,
    ALL_BUT_BOTTOM,
    ALL_BUT_TOP_AND_BOTTOM,
    ALL_SIDES,
}


@BindingAdapter(value = ["showVerticalList", "items"], requireAll = false)
fun <T> RecyclerView.bindRecyclerViewAdapter(adapter: ListAdapter<*, *>, list: List<T>?) {
    this.run {
        this.configureVerticalList(adapter, 0)
        this.adapter = adapter
    }
    if (this.adapter is ListAdapter<*, *>) {
        (adapter as ListAdapter<T, *>).submitList(list)

    }
}

fun <T, VH : RecyclerView.ViewHolder> RecyclerView.configureVerticalList(
    adapter: ListAdapter<T, VH>,
    margin: Int = 20.toPx(),
) {

    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    setHasFixedSize(true)
    addItemMargins(margin, MarginStrategy.ONLY_BOTTOM)
    this.adapter = adapter

}

fun RecyclerView.addItemMargins(
    spaceHeight: Int,
    marginStrategy: MarginStrategy = MarginStrategy.ONLY_TOP,
) {

    addItemDecoration(MarginItemDecoration(spaceHeight, marginStrategy))
}


class MarginItemDecoration(
    private val spaceHeight: Int,
    private val marginStrategy: MarginStrategy,
) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
//        val itemCount = state.itemCount

        when (marginStrategy) {
            MarginStrategy.ONLY_TOP -> with(outRect) { top = spaceHeight }
            MarginStrategy.ONLY_RIGHT -> with(outRect) { right = spaceHeight }
            MarginStrategy.ALL_BUT_TOP -> if (position > 0) with(outRect) { top = spaceHeight }
            MarginStrategy.ALL_SIDES -> if (position > -1) with(outRect) {
                top = spaceHeight
                left = spaceHeight
                right = spaceHeight
                bottom = spaceHeight
            }
            MarginStrategy.ONLY_BOTTOM -> with(outRect) { bottom = spaceHeight }
            MarginStrategy.ALL_BUT_BOTTOM -> TODO()
            MarginStrategy.ALL_BUT_TOP_AND_BOTTOM -> TODO()
        }

    }
}