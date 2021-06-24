package com.kerjapraktek.gunungindonesia.ui.peralatan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kerjapraktek.gunungindonesia.R
import com.kerjapraktek.gunungindonesia.databinding.ActivityPeralatanBinding
import com.kerjapraktek.gunungindonesia.ui.main.adapter.MenuListAdapter
import com.kerjapraktek.gunungindonesia.ui.peralatan.adapter.ListPeralatan
import com.kerjapraktek.gunungindonesia.viewmodel.InfoViewModel
import com.kerjapraktek.gunungindonesia.viewmodel.PeralatanViewModel

class PeralatanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeralatanBinding
    private lateinit var rvPeralatan: RecyclerView
    private lateinit var listPeralatan:ListPeralatan
    private lateinit var viewModel: PeralatanViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peralatan)
        binding = ActivityPeralatanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(PeralatanViewModel::class.java)
        val upArrow =resources.getDrawable(R.drawable.ic_baseline_chevron_left_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + getString(R.string.peralatan) + "</font>")

        showData()

    }

    private fun showData() {
        viewModel.setPeralatan()
        viewModel.getPeralatan().observe(this,{data->
            rvPeralatan=binding.rvPeralatan
            rvPeralatan.setHasFixedSize(true)
            rvPeralatan.layoutManager = LinearLayoutManager(this)
            listPeralatan = ListPeralatan(data)
            rvPeralatan.adapter = listPeralatan
        })

    }
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