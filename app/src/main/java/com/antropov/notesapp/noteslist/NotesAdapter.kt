package com.antropov.notesapp.noteslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antropov.notesapp.data.Note
import com.antropov.notesapp.databinding.NoteViewBinding

class NotesAdapter : ListAdapter<Note, NotesAdapter.ViewHolder>(NoteDiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  class ViewHolder private constructor(private val binding: NoteViewBinding) :
      RecyclerView.ViewHolder(binding.root) {

    init {
      binding.setClickListener { view ->
        binding.note?.noteId?.let { noteId ->
          navigateToNote(noteId, view)
        }
      }
    }

    fun bind(note: Note) {
      binding.note = note
      binding.executePendingBindings()
    }

    private fun navigateToNote(noteId: Int, view: View) {
      val direction = NotesListFragmentDirections
          .actionContactListFragmentToNoteFragment(noteId)
      view.findNavController()
          .navigate(direction)
    }

    companion object {
      fun from(parent: ViewGroup): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NoteViewBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
      }
    }
  }
}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
  override fun areItemsTheSame(oldNote: Note, newNote: Note): Boolean {
    return oldNote.noteId == newNote.noteId
  }

  override fun areContentsTheSame(oldNote: Note, newNote: Note): Boolean {
    return oldNote == newNote
  }
}
