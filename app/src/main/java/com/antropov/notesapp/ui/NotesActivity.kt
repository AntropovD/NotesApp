package com.antropov.notesapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.antropov.notesapp.R
import com.antropov.notesapp.util.RC_SIGN_IN
import com.antropov.notesapp.util.USERNAME
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NotesActivity: DaggerAppCompatActivity() {

  @Inject
  lateinit var firebaseAuth: FirebaseAuth

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var authStateListener: FirebaseAuth.AuthStateListener

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_notes)
    setSupportActionBar(findViewById(R.id.toolbar))
    setupActionBar()
    authStateListener = authStateListener()
  }

  override fun onResume() {
    super.onResume()
    firebaseAuth.addAuthStateListener(authStateListener)
  }

  override fun onPause() {
    super.onPause()
    firebaseAuth.removeAuthStateListener(authStateListener)
  }

  private fun createSignInIntent() {
    val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build())

    startActivityForResult(
        AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setIsSmartLockEnabled(false)
            .setAvailableProviders(providers)
            .build(),
        RC_SIGN_IN)
  }

  private fun authStateListener(): FirebaseAuth.AuthStateListener {
    return FirebaseAuth.AuthStateListener {
      val user = it.currentUser
      if (user == null)
        createSignInIntent()
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == RC_SIGN_IN) {
      if (resultCode == Activity.RESULT_OK) {
        val user = FirebaseAuth.getInstance().currentUser
        val intent = Intent(applicationContext, NotesActivity::class.java)
        intent.putExtra(USERNAME, user)
        startActivity(intent)
      } else if (resultCode == Activity.RESULT_CANCELED) {
        finish()
      }
    }
  }

  private fun setupActionBar() {
    val navController: NavController = findNavController(
        R.id.nav_host_fragment)
    appBarConfiguration = AppBarConfiguration
        .Builder(R.id.notesListFragment,
            R.id.noteFragment).build()
    setupActionBarWithNavController(navController, appBarConfiguration)
  }

  override fun onSupportNavigateUp(): Boolean {
    return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
        || super.onSupportNavigateUp()
  }
}
