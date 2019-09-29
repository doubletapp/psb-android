package com.doubletapp.psbhack.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.extensions.hideKeyboard
import com.doubletapp.psbhack.extensions.onDoneClick
import com.doubletapp.psbhack.main.MainActivity
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.android.synthetic.main.fragment_main_auth.*

class AuthFragment : Fragment() {

    private val viewModel by activityViewModels<AuthViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth_main_button.setOnClickListener {
            viewModel.onAuthButtonClick(auth_main_edit.text?.toString().orEmpty())
        }
        with(auth_main_edit) {
            onDoneClick {
                hideKeyboard()
                viewModel.onAuthButtonClick(auth_main_edit.text?.toString().orEmpty())
            }
        }
        auth_main_edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                auth_main_button.isEnabled = !auth_main_edit.text.isNullOrBlank()
            }
        })
        auth_main_button.isEnabled = !auth_main_edit.text.isNullOrBlank()

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            auth_main_progress.visibility = when (loading) {
                true -> View.VISIBLE
                else -> View.GONE
            }
        }
        viewModel.showVkDialog.observe(viewLifecycleOwner) {
            VkAuthDialog().show(childFragmentManager, VkAuthDialog.TAG)
        }
        viewModel.showNextScreen.observe(viewLifecycleOwner) { requestId: String? ->
            val intent = MainActivity.getIntent(requireContext(), requestId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    fun onVkAuthDecline() = viewModel.onVkAuthDecline()

    fun onVkAuthLogin(token: VKAccessToken) {
        viewModel.onVkAuthLogin(auth_main_edit.text?.toString().orEmpty(), token)
    }
}