package com.loan555.caculatorapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.loan555.caculatorapplication.R
import com.loan555.caculatorapplication.model.ItemButton

class ItemButtonAdapter(private val list: List<ItemButton>, private val onClick: (Int,ItemButton) -> Unit) :
    RecyclerView.Adapter<ItemButtonAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textButton)
        private val itemLayout: FrameLayout = itemView.findViewById(R.id.itemLayout)
        fun onBind(itemButton: ItemButton) {
            textView.text = itemButton.text
            itemLayout.setOnClickListener { onClick( layoutPosition,itemButton) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_button_adapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}