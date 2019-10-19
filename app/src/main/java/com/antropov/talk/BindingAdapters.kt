package com.antropov.talk

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antropov.talk.contactlist.ItemsAdapter
import com.antropov.talk.data.Item

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data:List<Item>){
  val adapter = recyclerView.adapter as ItemsAdapter
  adapter.submitList(data)
}