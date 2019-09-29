package com.doubletapp.psbhack.main_npd

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.doubletapp.psbhack.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_main_npd_operation.*

class MainNpdAdapter : RecyclerView.Adapter<MainNpdAdapter.MainNpdViewHolder>() {

    private val items = mutableListOf<OperationModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainNpdViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_npd_operation, parent, false)
        return MainNpdViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainNpdViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun submitItems(items: List<OperationModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class MainNpdViewHolder(

            override val containerView: View

    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: OperationModel) {
            item_main_npd_operation_name.text = item.name
            item_main_npd_operation_date.text = item.date
            if (item.sum.toLong() > 0) {
                item_main_npd_operation_sum.text = String.format("+ %.2f", item.sum.toFloat())
                item_main_npd_operation_sum.setTextColor(Color.parseColor("#49a15e"))
                item_main_npd_operation_sum_corruncy.setTextColor(Color.parseColor("#49a15e"))
                item_main_npd_operation_nalog.visibility = View.VISIBLE
                item_main_npd_operation_nalog.text = String.format("Налог: %.2f \u20BD", when (item.withFiz) {
                    true -> item.sum.toFloat() * 0.04
                    else -> item.sum.toFloat() * 0.06
                })
            } else if (item.sum.toLong() < 0) {
                item_main_npd_operation_sum.text = String.format("- %.2f", item.sum.removePrefix("-").toFloat())
                item_main_npd_operation_sum.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
                item_main_npd_operation_sum_corruncy.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
                item_main_npd_operation_nalog.visibility = View.GONE
            } else {
                item_main_npd_operation_sum.text = String.format("%.2f", item.sum.toFloat())
                item_main_npd_operation_sum.setTextColor(ContextCompat.getColor(itemView.context, R.color.black2))
                item_main_npd_operation_sum_corruncy.setTextColor(ContextCompat.getColor(itemView.context, R.color.black2))
                item_main_npd_operation_nalog.visibility = View.GONE
            }
        }
    }
}