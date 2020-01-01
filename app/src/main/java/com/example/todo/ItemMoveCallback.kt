package com.example.todo

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView


class ItemMoveCallback(private val adapter: Adapter, private val viewModel: MainViewModel): ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(UP or DOWN, RIGHT)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        val targetId = viewModel.entries.value!![target.adapterPosition].id
        val id = viewModel.entries.value!![viewHolder.adapterPosition].id

        viewModel.swap(id, targetId)
        return true
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val id = viewModel.entries.value!![viewHolder.adapterPosition].id
        viewModel.delete(id)
        //adapter.delete(viewHolder.adapterPosition)
    }
}
