package com.antropov.talk.contactlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antropov.talk.data.Item
import com.antropov.talk.databinding.ItemViewBinding

class ItemsAdapter : ListAdapter<Item, ItemsAdapter.ViewHolder>(DiffCallback) {

  class ViewHolder(private val binding: ItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
      binding.setClickListener { view ->
        binding.item?.id?.let { noteId ->
          navigateToNote(noteId, view)
        }
      }
    }

    private fun navigateToNote(
      noteId: Int,
      view: View
    ) {
      val direction = ContactListFragmentDirections
          .actionContactListFragmentToNoteFragment(noteId)
      view.findNavController()
          .navigate(direction)
    }

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

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = ItemViewBinding.inflate(
      LayoutInflater.from(parent.context), parent, false
    )
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}