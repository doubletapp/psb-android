package com.doubletapp.psbhack.screens.my_flat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doubletapp.psbhack.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_payment_history.*

class PaymentsAdapter : RecyclerView.Adapter<PaymentsAdapter.PaymentsViewHolder>() {

    private val items = mutableListOf<PaymentModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_payment_history, parent, false)
        return PaymentsViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PaymentsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun sumbitList(items: List<PaymentModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class PaymentsViewHolder(

            override val containerView: View

    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: PaymentModel) {
            payments_history_date.text = item.date
            payments_history_cost.text = item.cost
        }
    }
}