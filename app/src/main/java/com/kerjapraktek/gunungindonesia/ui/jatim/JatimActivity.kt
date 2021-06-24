package com.kerjapraktek.gunungindonesia.ui.jatim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kerjapraktek.gunungindonesia.R
import com.kerjapraktek.gunungindonesia.databinding.ActivityJatimBinding
import com.kerjapraktek.gunungindonesia.ui.jatim.adapter.GridJatimAdapter
import com.kerjapraktek.gunungindonesia.ui.peralatan.adapter.ListPeralatan
import com.kerjapraktek.gunungindonesia.viewmodel.GunungViewModel
import com.kerjapraktek.gunungindonesia.viewmodel.PeralatanViewModel

class JatimActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJatimBinding
    private lateinit var rvGunung: RecyclerView
    private lateinit var gridJatimAdapter: GridJatimAdapter
    private lateinit var viewModel: GunungViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jatim)
        binding = ActivityJatimBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val upArrow =resources.getDrawable(R.drawable.ic_baseline_chevron_left_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + getString(R.string.jawa_timur) + "</font>")

        viewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(GunungViewModel::class.java)
        showGunung()
    }

    private fun showGunung() {
        viewModel.setGunung("Jawa Timur")
        viewModel.getGunung().observe(this,{data->
            showLoading(false)
            rvGunung=binding.rvGunung
            rvGunung.setHasFixedSize(true)
            rvGunung.layoutManager = GridLayoutManager(this,2)
            gridJatimAdapter = GridJatimAdapter(data)
            rvGunung.adapter = gridJatimAdapter
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
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}