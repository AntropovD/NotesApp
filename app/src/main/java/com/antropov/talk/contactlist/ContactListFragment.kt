package com.antropov.talk.contactlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
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
    return binding.root
  }
}
