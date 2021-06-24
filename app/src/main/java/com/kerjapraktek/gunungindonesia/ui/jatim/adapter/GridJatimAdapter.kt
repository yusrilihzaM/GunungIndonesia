package com.kerjapraktek.gunungindonesia.ui.jatim.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kerjapraktek.gunungindonesia.R
import com.kerjapraktek.gunungindonesia.databinding.ItemGunungBinding
import com.kerjapraktek.gunungindonesia.databinding.ItemPeralatanBinding
import com.kerjapraktek.gunungindonesia.model.Gunung
import com.kerjapraktek.gunungindonesia.model.Peralatan
import com.kerjapraktek.gunungindonesia.ui.peralatan.adapter.ListPeralatan

class GridJatimAdapter(private val ListGunung: ArrayList<Gunung>): RecyclerView.Adapter<GridJatimAdapter.ListViewHolder>() {

    private var onItemClickCallback: GridJatimAdapter.OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: GridJatimAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(private val binding: ItemGunungBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gunung: Gunung){
            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            with(binding){
                photo?.let {
                    Glide.with(itemView.context)
                        .load(gunung.gambar_gunung)
                        .apply( RequestOptions.placeholderOf(circularProgressDrawable)
                            .error(R.drawable.ic_error)).override(155,155)
                        .into(it)
                }
                title.text=gunung.nama_gunung
                jenis.text=gunung.lokasi_gunung
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding=ItemGunungBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(ListGunung[position])
    }

    override fun getItemCount(): Int {
        return ListGunung.size
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Gunung)
    }
}