package com.kerjapraktek.gunungindonesia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kerjapraktek.gunungindonesia.R
import com.kerjapraktek.gunungindonesia.databinding.ItemPeralatanBinding
import com.kerjapraktek.gunungindonesia.model.Peralatan

class ListPeralatan (private val ListPeralatan: ArrayList<Peralatan>): RecyclerView.Adapter<ListPeralatan.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemPeralatanBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(peralatan: Peralatan){
            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            with(binding){
                photo?.let {
                    Glide.with(itemView.context)
                        .load(peralatan.gambar_peralatan)
                        .apply( RequestOptions.placeholderOf(circularProgressDrawable)
                            .error(R.drawable.ic_error)).override(155,155)
                        .into(it)
                }
                title.text=peralatan.nama_peralatan
                jenis.text=peralatan.jenis_peralatan

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding=ItemPeralatanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(ListPeralatan[position])
    }

    override fun getItemCount(): Int {
        return ListPeralatan.size
    }
}