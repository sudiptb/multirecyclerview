package com.example.multirecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.multirecyclerview.databinding.ItemChildLayoutBinding
import com.example.multirecyclerview.databinding.ItemLayoutBinding

class UserChildAdapter(
   val onClickItem: (List<Product>) -> Unit
) : ListAdapter<Product, CustomViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return false
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemChildLayoutBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = getItem(position)
        (holder.binding as ItemChildLayoutBinding).apply {
            tvName.text = currentItem.productName
            ivImage.setImageResource(currentItem.image)
            ivClose.setOnClickListener {
                removeItem(holder.adapterPosition)
            }

        }
    }

    private fun removeItem(pos: Int) {
        val list = currentList.toMutableList()
        list.removeAt(pos)
        submitList(list)
        onClickItem(list)
    }

    interface OnClickItem<E> {
        fun onClick(items: E, view: View, position: Int)
    }
}