package com.antropov.notesapp.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.antropov.notesapp.R
import com.antropov.notesapp.databinding.NoteFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class NoteFragment : DaggerFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private val viewModel by viewModels<NoteViewModel> { viewModelFactory }

  private lateinit var binding: NoteFragmentBinding

  private val args: NoteFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.note_fragment, container, false)
    binding = NoteFragmentBinding.bind(root)
    binding.viewModel = viewModel
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    binding.lifecycleOwner = this
    setupFab(binding)
    setupSnackbar()
    viewModel.start(args.noteId)
  }

  private fun setupSnackbar() {
    viewModel.snackbarErrorEvent.observe(viewLifecycleOwner, Observer {
      view?.let {
        Snackbar.make(
            it,
            getString(R.string.snackbar_note_fragment_error),
            Snackbar.LENGTH_LONG
        )
            .show()
      }
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
    val action =
        NoteFragmentDirections.actionNoteFragmentToNotesListFragment()
    NavHostFragment.findNavController(this)
        .navigate(action)
  }
}
