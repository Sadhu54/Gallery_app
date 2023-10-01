package com.thoughtctl.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(
    private val spanCount: Int,  // Number of columns in the grid
    private val spacing: Int    // Spacing between items in pixels
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // Item position
        val column = position % spanCount // Item column

        // Calculate left and right offsets to achieve equal spacing between items
        val hSpacing = spacing - column * spacing / spanCount
        val vSpacing = spacing

        outRect.apply {
            left = hSpacing
            right = spacing - (column + 1) * hSpacing
            top = vSpacing
            bottom = vSpacing
        }
    }
}
