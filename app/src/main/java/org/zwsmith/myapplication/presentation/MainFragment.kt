package org.zwsmith.myapplication.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Button
import android.widget.Toast

import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import org.zwsmith.myapplication.R
import org.zwsmith.myapplication.MyApplication
import org.zwsmith.myapplication.core.dependencyInjection.AppComponent

import java.util.Collections
import java.util.Objects

import android.app.Activity.RESULT_OK

class MainFragment : Fragment() {

    private var appComponent: AppComponent? = null
    private var mainViewModel: MainViewModel? = null
    private val authProviders = listOf<IdpConfig>(AuthUI.IdpConfig.GoogleBuilder().build())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myApplication = Objects.requireNonNull<FragmentActivity>(activity).getApplicationContext() as MyApplication
        appComponent = myApplication.appComponent
        mainViewModel = appComponent!!.mainViewModel
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionButton = view.findViewById<Button>(R.id.primary_action_button)

        actionButton.setOnClickListener { startSignInActivity(authProviders) }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == RESULT_OK) {
                mainViewModel!!.createUser()
            } else {
                if (response != null) {
                    val message = Objects.requireNonNull<FirebaseUiException>(response.error).message
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun startSignInActivity(providers: List<AuthUI.IdpConfig>) {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN
        )
    }

    companion object {
        private val TAG = MainFragment::class.java!!.getName()
        private val RC_SIGN_IN = 123

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
