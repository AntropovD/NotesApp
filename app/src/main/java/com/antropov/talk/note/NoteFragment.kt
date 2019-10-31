package com.antropov.talk.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.antropov.talk.databinding.NoteFragmentBinding

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
  }

  private fun setupFab(binding: NoteFragmentBinding) {
    binding.fabNoteFragment.setOnClickListener {
      viewModel.increment(
        binding.editTextNoteFragmentTitle.text,
        binding.editTextNoteFragmentDescription.text
      )
      navigateBack()
    }
  }

  private fun navigateBack() {
    val action = NoteFragmentDirections.actionNoteFragmentToContactListFragment("1")
    NavHostFragment.findNavController(this).navigate(action)
  }
}
