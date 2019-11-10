package com.antropov.talk.noteslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
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
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = NoteListFragmentBinding.inflate(inflater, container, false)
    binding.viewModel = viewModel
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)


    setHasOptionsMenu(true)
    setupListAdapter()
    setupNavigation()
    setupFab(binding)
    binding.lifecycleOwner = this
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

  private fun setupListAdapter() {
    val viewModel = binding.viewModel
    if (viewModel != null) {
      val listAdapter = NotesAdapter()
      binding.recyclerViewContactListFragment.adapter = listAdapter
    } else {
      Log.d("Tag", "adapter not initialized")
    }
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

  private fun navigateToEditNote(noteId: Int) {
    val action = NotesListFragmentDirections.actionContactListFragmentToNoteFragment(noteId)
    NavHostFragment.findNavController(this)
        .navigate(action)
  }

  private fun navigateToNewNote() {
    val action = NotesListFragmentDirections.actionContactListFragmentToNoteFragment()
    NavHostFragment.findNavController(this)
        .navigate(action)
  }
}
