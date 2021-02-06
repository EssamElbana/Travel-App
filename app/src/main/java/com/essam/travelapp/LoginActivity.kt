package com.essam.travelapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        textUsernameLayout.editText?.addTextChangedListener(createTextWatcher(textUsernameLayout))
        textPasswordLayout.editText?.addTextChangedListener(createTextWatcher(textPasswordLayout))

        loginButton.setOnClickListener {
            loginButtonClicked()
        }
    }

    private fun loginButtonClicked() {

        val username = textUsernameLayout.editText?.text ?: ""
        val password = textPasswordLayout.editText?.text ?: ""

        if(username.isBlank() && username.isEmpty() || password.isEmpty() && password.isBlank())
            return

        if (username != "admin" || password != "admin")
            AlertDialog.Builder(this)
                .setTitle("Login Failed")
                .setMessage("Username or password is not correct. Please try again.")
                .setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        else {
            performLogin()
        }
    }

    private fun performLogin() {
        loginButton.visibility = View.INVISIBLE
        loginButton.isEnabled = false
        textUsernameLayout.isEnabled = false
        textPasswordLayout.isEnabled = false
        progressBar.visibility = View.VISIBLE
        Handler().postDelayed({
            startMainActivity()
        }, 2000)
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }


    private fun createTextWatcher(textPasswordInput: TextInputLayout): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int, count: Int, after: Int
            ) {
                // not needed
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int, before: Int, count: Int
            ) {
                textPasswordInput.error = null
            }

            override fun afterTextChanged(s: Editable) {
                // not needed
            }
        }
    }
}