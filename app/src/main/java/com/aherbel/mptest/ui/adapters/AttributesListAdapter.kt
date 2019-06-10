package com.aherbel.mptest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aherbel.mptest.databinding.RvItemAttributeBinding
import com.aherbel.mptest.model.Attribute
import com.aherbel.mptest.ui.viewholders.AttributeViewHolder

class AttributesListAdapter(private val attributes: ArrayList<Attribute>) :
    RecyclerView.Adapter<AttributeViewHolder>() {

    override fun getItemCount(): Int = attributes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttributeViewHolder {
        val binding = RvItemAttributeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttributeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttributeViewHolder, position: Int) {
        holder.bind(attributes[position])
    }
}