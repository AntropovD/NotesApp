package com.antropov.notesapp.ui.noteslist

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antropov.notesapp.data.entity.Note

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Note>) {
  (recyclerView.adapter as NotesAdapter).submitList(data)
  recyclerView.scrollToPosition(data.size - 1)
}