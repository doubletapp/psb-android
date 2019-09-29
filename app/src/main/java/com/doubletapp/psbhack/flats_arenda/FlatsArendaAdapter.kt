package com.doubletapp.psbhack.flats_arenda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doubletapp.psbhack.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_flats_arenda.*

class FlatsArendaAdapter(

        private val action: (FlatModel) -> Unit,
        private val edit: (FlatModel) -> Unit

) : RecyclerView.Adapter<FlatsArendaAdapter.FlatsArendaViewHolder>() {

    private val items = mutableListOf<FlatModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlatsArendaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flats_arenda, parent, false)
        return FlatsArendaViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FlatsArendaViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun submitItems(items: List<FlatModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


    inner class FlatsArendaViewHolder(

            override val containerView: View

    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            flats_arenda_clickable_view.setOnClickListener {
                action(items[adapterPosition])
            }
            edit.setOnClickListener {
                edit(items[adapterPosition])
            }
        }

        fun bind(item: FlatModel) {
            flats_arenda_name.text = item.address
            flats_arenda_status.text = item.status
        }
    }
}