package com.antropov.talk.noteslist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.antropov.talk.R
import com.antropov.talk.databinding.NoteListFragmentBinding
import com.antropov.talk.util.EventObserver
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class NotesListFragment : DaggerFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private val viewModel by viewModels<NotesListViewModel> { viewModelFactory }

  private lateinit var binding: NoteListFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = NoteListFragmentBinding.inflate(inflater)
    binding.viewModel = viewModel
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    binding.lifecycleOwner = this
    binding.recyclerViewContactListFragment.adapter = NotesAdapter()
    setHasOptionsMenu(true)
    setupFab(binding)
    setupNavigation()
  }

  private fun setupFab(binding: NoteListFragmentBinding) {
    binding.fabContactListFragment.setOnClickListener {
      navigateToNewNote()
    }
  }

  private fun setupNavigation() {
    viewModel.openNoteEvent.observe(this, EventObserver {
      navigateToEditNote(it)
    })
  }

  private fun navigateToEditNote(noteId: Int) {
    val action = NotesListFragmentDirections.actionContactListFragmentToNoteFragment(noteId)
    NavHostFragment.findNavController(this)
        .navigate(action)
  }

  private fun navigateToNewNote() {
    val action = NotesListFragmentDirections.actionContactListFragmentToNoteFragment()
    NavHostFragment.findNavController(this).navigate(action)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.menu, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem) =
    when (item.itemId) {
      R.id.clear_menu -> {
        viewModel.clearView()
        true
      }
      else -> {
        super.onOptionsItemSelected(item)
      }
    }
}
