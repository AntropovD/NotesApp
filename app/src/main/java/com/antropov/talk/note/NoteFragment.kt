package com.antropov.talk.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.antropov.talk.R
import com.antropov.talk.databinding.NoteFragmentBinding
import com.google.android.material.snackbar.Snackbar

class NoteFragment : Fragment() {

  private val viewModel: NoteViewModel by lazy {
    ViewModelProviders.of(this).get(NoteViewModel::class.java)
  }
  private lateinit var binding: NoteFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = NoteFragmentBinding.inflate(inflater)
    binding.viewModel = viewModel
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    binding.lifecycleOwner = this
    setupFab(binding)
    setupSnackbar()
  }

  private fun setupSnackbar() {
    viewModel.snackbarErrorEvent.observe(viewLifecycleOwner, Observer {
      view?.let { Snackbar.make(it, getString(R.string.snackbar_note_fragment_error), Snackbar.LENGTH_LONG).show() }
    })
    viewModel.noteAddedEvent.observe(viewLifecycleOwner, Observer {
      navigateBack()
    })
  }

  private fun setupFab(binding: NoteFragmentBinding) {
    binding.fabNoteFragment.setOnClickListener {
      viewModel.addNote(
        binding.editTextNoteFragmentTitle.text.toString(),
        binding.editTextNoteFragmentDescription.text.toString()
      )
    }
  }

  private fun navigateBack() {
    val action = NoteFragmentDirections.actionNoteFragmentToContactListFragment("1")
    NavHostFragment.findNavController(this).navigate(action)
  }
}
