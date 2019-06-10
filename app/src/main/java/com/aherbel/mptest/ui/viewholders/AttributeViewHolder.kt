package com.aherbel.mptest.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.aherbel.mptest.databinding.RvItemAttributeBinding
import com.aherbel.mptest.model.Attribute

class AttributeViewHolder(private val binding: RvItemAttributeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(attribute: Attribute) {
        binding.apply {
            this.attribute = attribute
            executePendingBindings()
        }
    }
}