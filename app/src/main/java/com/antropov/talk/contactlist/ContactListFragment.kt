package com.antropov.talk.contactlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.antropov.talk.R
import com.antropov.talk.databinding.ContactListFragmentBinding
import com.antropov.talk.util.EventObserver

class ContactListFragment : Fragment() {

  private val viewModel: ContactListViewModel by lazy {
    ViewModelProviders.of(this).get(ContactListViewModel::class.java)
  }

  private lateinit var binding: ContactListFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = ContactListFragmentBinding.inflate(inflater)
    binding.viewModel = viewModel
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    binding.lifecycleOwner = this
    binding.recyclerViewContactListFragment.adapter = ItemsAdapter()
    setHasOptionsMenu(true)
    setupFab(binding)
    setupNavigation()
  }

  private fun setupFab(binding: ContactListFragmentBinding) {
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
    Log.d("123", "123")
    val action = ContactListFragmentDirections.actionContactListFragmentToNoteFragment(noteId)
    NavHostFragment.findNavController(this)
        .navigate(action)
  }

  private fun navigateToNewNote() {
    val action = ContactListFragmentDirections.actionContactListFragmentToNoteFragment()
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
