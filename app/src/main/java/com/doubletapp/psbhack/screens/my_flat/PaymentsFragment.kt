package com.doubletapp.psbhack.screens.my_flat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.flats_arenda.FlatModel
import kotlinx.android.synthetic.main.fragment_flat_payments.*

class PaymentsFragment : Fragment() {

    private val adapter by lazy { PaymentsAdapter() }

    companion object {

        const val ARG_FLAT = "flat"

        @JvmStatic fun newInstance(flat: FlatModel?) = PaymentsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_FLAT, flat)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_flat_payments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val flat = requireArguments().getParcelable<FlatModel>(ARG_FLAT)
        flat_payments_next_payment_date.text = flat?.nextPaymentDate
        flat_payments_next_payment_card.visibility = when (flat?.nextPaymentDate) {
            null -> View.GONE
            else -> View.VISIBLE
        }
        flat_payments_arendators_event_date.text = flat?.nextArendatorsEventDate
        flat_payments_arendators_event_card.visibility = when (flat?.nextArendatorsEventDate) {
            null -> View.GONE
            else -> View.VISIBLE
        }
        payments_history_list.adapter = adapter
        adapter.sumbitList(listOf(
                PaymentModel(
                        date = "24.08.19",
                        cost = "15 000,00 \u20BD"
                ),
                PaymentModel(
                        date = "24.08.19",
                        cost = "15 000,00 \u20BD"
                ),
                PaymentModel(
                        date = "24.08.19",
                        cost = "15 000,00 \u20BD"
                ),
                PaymentModel(
                        date = "24.08.19",
                        cost = "15 000,00 \u20BD"
                ),
                PaymentModel(
                        date = "24.08.19",
                        cost = "15 000,00 \u20BD"
                ),
                PaymentModel(
                        date = "24.08.19",
                        cost = "15 000,00 \u20BD"
                ),
                PaymentModel(
                        date = "24.08.19",
                        cost = "15 000,00 \u20BD"
                ),
                PaymentModel(
                        date = "24.08.19",
                        cost = "15 000,00 \u20BD"
                ),
                PaymentModel(
                        date = "24.08.19",
                        cost = "15 000,00 \u20BD"
                )
        ))
    }
}