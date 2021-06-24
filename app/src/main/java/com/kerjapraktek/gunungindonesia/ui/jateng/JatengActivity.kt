package com.kerjapraktek.gunungindonesia.ui.jateng

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kerjapraktek.gunungindonesia.R
import com.kerjapraktek.gunungindonesia.adapter.GridGunungAdapter
import com.kerjapraktek.gunungindonesia.databinding.ActivityJabarBinding
import com.kerjapraktek.gunungindonesia.databinding.ActivityJatengBinding
import com.kerjapraktek.gunungindonesia.viewmodel.GunungViewModel

class JatengActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJatengBinding
    private lateinit var rvGunung: RecyclerView
    private lateinit var gridGunungAdapter: GridGunungAdapter
    private lateinit var viewModel: GunungViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jateng)


        val upArrow =resources.getDrawable(R.drawable.ic_baseline_chevron_left_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + getString(R.string.jawa_tengah) + "</font>")
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