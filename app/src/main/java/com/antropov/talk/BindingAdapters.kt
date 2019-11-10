package com.antropov.talk

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antropov.talk.data.Note
import com.antropov.talk.noteslist.NotesAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Note>) {
  (recyclerView.adapter as NotesAdapter).submitList(data)
  recyclerView.scrollToPosition(data.size - 1)
}