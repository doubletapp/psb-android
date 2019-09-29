package com.doubletapp.psbhack.auth

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.doubletapp.psbhack.R
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.dialog_vk_auth.*

class VkAuthDialog : DialogFragment() {

    private val viewModel by activityViewModels<AuthViewModel>()

    companion object {

        const val TAG = "VkAuthDialog"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_vk_auth, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
                .apply { setCanceledOnTouchOutside(false) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vk_auth_connect.setOnClickListener {
            VK.login(requireActivity(), listOf(
                    VKScope.OFFLINE, // доступ к API в любое время, токен бессрочный
//                    VKScope.EMAIL, // почта
//                    VKScope.STATUS, // статус
                    VKScope.GROUPS, // группы
                    VKScope.MARKET, // товары
                    VKScope.WALL, // стена (посты)
                    VKScope.NOTES // стена (заметки)
            ))
        }
        vk_auth_back.setOnClickListener {
            dismiss()
        }
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            vk_auth_connect.isEnabled = !loading
            vk_auth_progress_bar.visibility = when (loading) {
                true -> View.VISIBLE
                else -> View.GONE
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        viewModel.onVkAuthDecline()
    }

    override fun onResume() {
        super.onResume()

        val background = ColorDrawable(Color.TRANSPARENT)
        val margin = 30
        dialog?.window?.setBackgroundDrawable(InsetDrawable(background, margin, 0, margin, 0))
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}