package com.doubletapp.psbhack.main_npd

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.extensions.replaceFragment
import kotlinx.android.synthetic.main.fragment_main_npd.*

class MainNpdFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainNpdViewModel::class.java) }

    private val adapter by lazy { MainNpdAdapter() }

    companion object {

        @JvmStatic
        fun newInstance() = MainNpdFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_npd, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main_npd_info_last_operations_see_all.movementMethod = LinkMovementMethod.getInstance()
        main_npd_info_last_operations_list.adapter = adapter
        StartSnapHelper().attachToRecyclerView(main_npd_info_last_operations_list)
        viewModel.operations.observe(viewLifecycleOwner, adapter::submitItems)
        viewModel.npdProfile.observe(viewLifecycleOwner) { profile ->
            main_npd_info_month.text = String.format("Выручка за %s", profile.nameLastMonth)
            main_npd_info_sum.text = String.format("%.2f", profile.sumLastMonth.toFloat())
            main_npd_info_dolg.text = String.format("%.2f", profile.dolg.toFloat())
            if (profile.isArendaConnected) {
                main_npd_info_service_arenda_status.text = "Подключен"
                main_npd_info_service_arenda_status.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.bg_npd_service_connected, 0)
            } else {
                main_npd_info_service_arenda_status.text = "Не подключен"
                main_npd_info_service_arenda_status.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.bg_npd_service_disconnected, 0)
            }
            if (profile.isClientConnected) {
                main_npd_info_service_client_status.text = "Подключен"
                main_npd_info_service_client_status.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.bg_npd_service_connected, 0)
            } else {
                main_npd_info_service_client_status.text = "Не подключен"
                main_npd_info_service_client_status.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.bg_npd_service_disconnected, 0)
            }
        }

        main_npd_info_new_check.setOnClickListener {
            replaceFragment(BillFragment.newInstance(), android.R.id.content, null)
        }

        main_npd_info_services_card_clickable_view.setOnClickListener {
            replaceFragment(ServiceFragment.newInstance(viewModel.npdProfile.value), android.R.id.content, null)  }
    }
}