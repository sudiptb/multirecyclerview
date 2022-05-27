package com.example.multirecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.multirecyclerview.databinding.ItemLayoutBinding

class UserParentAdapter(
    val onClickItem: (List<User>, UserParentAdapter) -> Unit
) : ListAdapter<User, CustomViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = getItem(position)
        (holder.binding as ItemLayoutBinding).apply {
            tvName.text = currentItem.name
            val userChildAdapter = UserChildAdapter{

                println("list is ${it.size}")

                currentItem.productList = it

                //onClickItem(currentList, this@UserParentAdapter)

                if (it.isEmpty()){
                    removeItem(holder.adapterPosition)
                }else{
                    println("ssssss")
                    println("current list is $currentList")
                    onClickItem(currentList, this@UserParentAdapter)

                }
            }
            userChildAdapter.submitList(currentItem.productList)
            rvProducts.apply {
                setHasFixedSize(true)
                adapter = userChildAdapter
            }
        }
    }

    fun removeItem(pos: Int) {
        val list = currentList.toMutableList()
        list.removeAt(pos)
        println("current list is : " + list.map {
            it.id
        })
        submitList(list)
    }

    interface OnClickItem<E> {
        fun onClick(items: E, view: View, position: Int)
    }
}