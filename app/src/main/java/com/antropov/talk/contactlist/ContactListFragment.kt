package com.antropov.talk.contactlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.antropov.talk.R
import com.antropov.talk.databinding.ContactListFragmentBinding

class ContactListFragment : Fragment() {

  private val viewModel: ContactListViewModel by lazy {
    ViewModelProviders.of(this).get(ContactListViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val binding = ContactListFragmentBinding.inflate(inflater)
    binding.lifecycleOwner = this
    binding.viewModel = viewModel
    binding.contacts.adapter = ItemsAdapter()
    binding.floatingActionButton.setOnClickListener {
      viewModel.increment()
    }
    setHasOptionsMenu(true)
    addRecyclerViewDivider(binding)
    setupFab(binding)

    return binding.root
  }

  private fun setupFab(binding: ContactListFragmentBinding) {
    binding.floatingActionButton.setOnClickListener {
      navigateToNewNote()
    }
  }

  private fun navigateToNewNote() {
    val action = ContactListFragmentDirections.actionContactListFragmentToNoteFragment()
    NavHostFragment.findNavController(this).navigate(action)
    viewModel.increment()
  }

  private fun addRecyclerViewDivider(binding: ContactListFragmentBinding) {
    val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    val dividerDrawable = ResourcesCompat.getDrawable(resources, R.drawable.divider, null)
    if (dividerDrawable != null) {
      decoration.setDrawable(dividerDrawable)
    }
    binding.contacts.addItemDecoration(decoration)
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
