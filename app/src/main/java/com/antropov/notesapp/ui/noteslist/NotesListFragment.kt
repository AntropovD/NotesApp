package com.antropov.notesapp.ui.noteslist

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
import com.antropov.notesapp.R
import com.antropov.notesapp.databinding.NoteListFragmentBinding
import com.antropov.notesapp.util.EventObserver
import com.google.firebase.auth.FirebaseAuth
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
        R.id.logout_menu -> {
          logout()
          true
        }
        else -> {
          super.onOptionsItemSelected(item)
        }
      }

  private fun navigateToEditNote(noteId: Int) {
    val action = NotesListFragmentDirections.actionNotesListFragmentToNoteFragment(noteId)
    NavHostFragment.findNavController(this)
        .navigate(action)
  }

  private fun navigateToNewNote() {
    val action = NotesListFragmentDirections.actionNotesListFragmentToNoteFragment()
    NavHostFragment.findNavController(this)
        .navigate(action)
  }

  private fun logout() {
    FirebaseAuth.getInstance().signOut()
  }
}
