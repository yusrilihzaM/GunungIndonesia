package com.kerjapraktek.gunungindonesia.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kerjapraktek.gunungindonesia.databinding.ItemMainMenuBinding
import com.kerjapraktek.gunungindonesia.model.Menu

class MenuListAdapter (private val listMenu: ArrayList<Menu>): RecyclerView.Adapter<MenuListAdapter.ListViewHolder>(){
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(private val binding: ItemMainMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: Menu){
            with(binding){
                Glide.with(itemView.context)
                    .load(menu.ic)
                    .into(icMenu)
                tvTitle.text = menu.title
                itemView.setOnClickListener{
                    onItemClickCallback?.onItemClicked(menu)
                }
            }
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Menu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding=ItemMainMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMenu[position])
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }
}