package com.antropov.talk.noteslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antropov.talk.data.Note
import com.antropov.talk.databinding.NoteViewBinding

class NotesAdapter : ListAdapter<Note, NotesAdapter.ViewHolder>(DiffCallback) {

  class ViewHolder(private val binding: NoteViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
      binding.setClickListener { view ->
        binding.note?.id?.let { noteId ->
          navigateToNote(noteId, view)
        }
      }
    }

    private fun navigateToNote(
      noteId: Int,
      view: View
    ) {
      val direction = NotesListFragmentDirections
          .actionContactListFragmentToNoteFragment(noteId)
      view.findNavController()
          .navigate(direction)
    }

    fun bind(note: Note) {
      binding.note = note
      binding.executePendingBindings()
    }
  }

  companion object DiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(
      oldNote: Note,
      newNote: Note
    ): Boolean {
      return oldNote === newNote
    }

    override fun areContentsTheSame(
      oldNote: Note,
      newNote: Note
    ): Boolean {
      return oldNote.id == newNote.id
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = NoteViewBinding.inflate(
      LayoutInflater.from(parent.context), parent, false
    )
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}