package com.antropov.talk.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.antropov.talk.R

class NoteFragment : Fragment() {

  companion object {
    fun newInstance() = NoteFragment()
  }

  private lateinit var viewModel: NoteViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.note_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
    // TODO: Use the ViewModel
  }

}
