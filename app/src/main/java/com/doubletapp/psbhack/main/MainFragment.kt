package com.doubletapp.psbhack.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.auth.AuthActivity
import com.doubletapp.psbhack.base.GlideApp
import com.doubletapp.psbhack.main_npd.MainNpdActivity
import com.doubletapp.psbhack.main_npd.RegTokenPreference
import com.doubletapp.psbhack.screens.service_connection.ServiceConnectionActivity
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()

    private val regPreference by lazy { RegTokenPreference() }

    companion object {

        private const val ARGS_REQUEST_ID = "args_request_id"

        @JvmStatic
        fun newInstance(requestId: String?) = MainFragment().apply {
            arguments = Bundle().apply {
                putString(ARGS_REQUEST_ID, requestId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main_fab.setOnClickListener {  }
        main_npd_clickable_view.setOnClickListener { openServiceConnection() }
        main_npd_text.setOnClickListener { openServiceConnection() }
        viewModel.setRequestId(arguments?.getString(ARGS_REQUEST_ID))
        viewModel.npdInfo.observe(viewLifecycleOwner) { result ->
            if (result.result) {
                main_npd_title.text = result.title
                main_npd_subtitle.text = result.subtitile
                GlideApp
                        .with(requireContext())
                        .load(result.imageUrl)
                        .into(main_npd_background)
            }
        }
        viewModel.loggedOut.observe(viewLifecycleOwner) {
            val intent = Intent(requireContext(), AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    private fun openServiceConnection() {
        context?.let {
            if (regPreference.isRegFinished())
                startActivity(MainNpdActivity.getIntent(it))
            else
                startActivity(Intent(requireContext(), ServiceConnectionActivity::class.java))
        }
    }
}