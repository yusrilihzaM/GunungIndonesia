package com.kerjapraktek.gunungindonesia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kerjapraktek.gunungindonesia.R
import com.kerjapraktek.gunungindonesia.databinding.ItemGunungBinding
import com.kerjapraktek.gunungindonesia.model.Gunung

class GridGunungAdapter(private val ListGunung: ArrayList<Gunung>): RecyclerView.Adapter<GridGunungAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    //nampilkan data
    inner class ListViewHolder(private val binding: ItemGunungBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gunung: Gunung){
            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            with(binding){
                //start foto gunung
                photo?.let {
                    Glide.with(itemView.context)
                        .load(gunung.gambar_gunung)
                        .apply( RequestOptions.placeholderOf(circularProgressDrawable)
                            .error(R.drawable.ic_error)).override(155,155)
                        .into(it)
                }
                //end foto gunung
                title.text=gunung.nama_gunung// nama gunung
                jenis.text=gunung.lokasi_gunung//lokasi
                //start klik
                itemView.setOnClickListener{
                    onItemClickCallback?.onItemClicked(gunung)
                }
                //end klik
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