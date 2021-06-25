package com.kerjapraktek.gunungindonesia.ui.detail

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.snackbar.Snackbar
import com.kerjapraktek.gunungindonesia.R
import com.kerjapraktek.gunungindonesia.databinding.ActivityDetailBinding
import com.kerjapraktek.gunungindonesia.model.Gunung


class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        //data hasil kirim gunung
        val data=intent.getParcelableExtra<Gunung>(EXTRA_DATA) as Gunung

        binding.toolbarLayout.setExpandedTitleTextAppearance(R.style.expandedToolbarLayoutTitleColor);
        binding.toolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor);
        binding.toolbarLayout.title = data.nama_gunung//judul gunung

        val upArrow =resources.getDrawable(R.drawable.ic_baseline_chevron_left_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + data.nama_gunung + "</font>")


        //menampilkan gambar
        val uri= Uri.parse(data.gambar_gunung)
        //tampilkan ke ui
        Glide.with(this)
            .load(uri)
            .into(object : CustomTarget<Drawable?>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    binding.appBar.background = resource
                }
            })
        //panggil ui
        val lokasiGunung:TextView=findViewById(R.id.lokasi_gunung)
        val deskripsiGunung:TextView=findViewById(R.id.deskripsi_gunung)
        val jalurPendakian:TextView=findViewById(R.id.jalur_pendakian)
        val estimasiPendakian:TextView=findViewById(R.id.estimasi_pendakian)
        val informasiGunung:TextView=findViewById(R.id.informasi_gunung)
        val hargaTiket:TextView=findViewById(R.id.harga_tiket)

        lokasiGunung.text=data.lokasi_gunung
        deskripsiGunung.text=data.deskripsi_gunung
        jalurPendakian.text=data.jalur_pendakian
        estimasiPendakian.text=data.estimasi_pendakian
        informasiGunung.text=data.informasi_gunung
        hargaTiket.text=data.harga_tiket
    }
    //supaya tombol back di klik
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            16908332->{
                this.finish()
                true
            }
            else -> true
        }
    }
}