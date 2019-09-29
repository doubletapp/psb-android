package com.doubletapp.psbhack.screens.service_connection

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doubletapp.psbhack.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.item_connection_third.*

class ConnectionThirdAdapter(

        val selectedItems: ArrayList<String>

) : RecyclerView.Adapter<ConnectionThirdAdapter.ConnectionThirdViewHolder>() {

    private val items = mutableListOf<ConnectionThirdItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectionThirdViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_connection_third, parent, false)
        return ConnectionThirdViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConnectionThirdViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitItems(items: List<ConnectionThirdItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    @Parcelize
    data class ConnectionThirdItem(

            val text: String,

            var checked: Boolean

    ) : Parcelable {

        override fun equals(other: Any?): Boolean {
            return text == (other as? ConnectionThirdItem)?.text
        }

        override fun hashCode(): Int = text.hashCode()
    }

    inner class ConnectionThirdViewHolder(

            override val containerView: View

    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            item_connection_third_clickable_view.setOnClickListener {
                if (items[adapterPosition].checked) {
                    selectedItems.remove(items[adapterPosition].text)
                    item_connection_third_check.performClick()
                    items[adapterPosition].checked = item_connection_third_check.isChecked
                } else if (selectedItems.size < 5) {
                    selectedItems.add(items[adapterPosition].text)
                    item_connection_third_check.performClick()
                    items[adapterPosition].checked = item_connection_third_check.isChecked
                }
            }
        }

        fun bind(item: ConnectionThirdItem) {
            item_connection_third_text.text = item.text
            item_connection_third_check.isChecked = item.checked
        }
    }
}