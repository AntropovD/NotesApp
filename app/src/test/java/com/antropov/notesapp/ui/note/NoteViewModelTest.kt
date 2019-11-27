package com.antropov.notesapp.ui.note

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.antropov.notesapp.data.FakeNotesRepository
import com.antropov.notesapp.getOrAwaitValue
import com.antropov.notesapp.ui.note.NoteViewModel
import com.antropov.notesapp.util.FakeDateTimeProvider
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteViewModelTest {

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  private lateinit var notesRepository: FakeNotesRepository

  private lateinit var noteViewModel: NoteViewModel

  @Before
  fun setupViewModel() {
    notesRepository = FakeNotesRepository()
    noteViewModel = NoteViewModel(notesRepository,
        FakeDateTimeProvider)
  }

  @Test
  fun addNote() {
    noteViewModel.addNote("title", "description")
    val value = noteViewModel.noteAddedEvent.getOrAwaitValue { Unit }
    assertThat(value.getContentIfNotHandled(), (not(nullValue())))
  }
}