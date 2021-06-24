package com.kerjapraktek.gunungindonesia.ui.jatim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kerjapraktek.gunungindonesia.R
import com.kerjapraktek.gunungindonesia.databinding.ActivityJatimBinding
import com.kerjapraktek.gunungindonesia.adapter.GridGunungAdapter
import com.kerjapraktek.gunungindonesia.model.Gunung
import com.kerjapraktek.gunungindonesia.ui.detail.DetailActivity
import com.kerjapraktek.gunungindonesia.ui.detail.DetailActivity.Companion.EXTRA_DATA
import com.kerjapraktek.gunungindonesia.viewmodel.GunungViewModel

class JatimActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJatimBinding
    private lateinit var rvGunung: RecyclerView
    private lateinit var gridGunungAdapter: GridGunungAdapter
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
            gridGunungAdapter = GridGunungAdapter(data)

            rvGunung.adapter = gridGunungAdapter
            gridGunungAdapter.setOnItemClickCallback(object :GridGunungAdapter.OnItemClickCallback{
                override fun onItemClicked(data: Gunung) {
                    Toast.makeText(this@JatimActivity, data.nama_gunung, Toast.LENGTH_SHORT).show()
                    val intent= Intent(this@JatimActivity, DetailActivity::class.java)
                    intent.putExtra(EXTRA_DATA,data)
                    startActivity(intent)
                }
            })
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