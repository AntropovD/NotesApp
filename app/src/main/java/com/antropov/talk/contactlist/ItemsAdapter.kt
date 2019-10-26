package com.antropov.talk.contactlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antropov.talk.data.Item
import com.antropov.talk.databinding.ItemViewBinding

class ItemsAdapter : ListAdapter<Item, ItemsAdapter.ViewHolder>(DiffCallback) {

  class ViewHolder(private val binding: ItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item) {
      binding.item = item
      binding.executePendingBindings()
    }
  }

  companion object DiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
      return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
      return oldItem.id == newItem.id
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ViewHolder {
    return ViewHolder(
      ItemViewBinding.inflate(
        LayoutInflater.from(
          parent.context
        )
      )
    )
  }

  override fun onBindViewHolder(holder: ItemsAdapter.ViewHolder, position: Int) {
    val item = getItem(position)
    holder.bind(item)
  }
}