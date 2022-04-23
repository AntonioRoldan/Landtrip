package io.keepcoding.mvvmarchitecture.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val childLayoutPoisition = parent.getChildLayoutPosition(view)
        outRect.left = space
        outRect.right = space
        outRect.bottom = space
        outRect.top = space
        if(childLayoutPoisition in 0..3){ // If position is between zero and three
            outRect.top = space * 2
        }
    }
}

