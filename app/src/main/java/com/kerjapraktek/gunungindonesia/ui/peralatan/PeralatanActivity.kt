package com.kerjapraktek.gunungindonesia.ui.peralatan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.kerjapraktek.gunungindonesia.R
import com.kerjapraktek.gunungindonesia.databinding.ActivityPeralatanBinding
import com.kerjapraktek.gunungindonesia.viewmodel.InfoViewModel

class PeralatanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeralatanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peralatan)
        binding = ActivityPeralatanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val upArrow =resources.getDrawable(R.drawable.ic_baseline_chevron_left_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + getString(R.string.peralatan) + "</font>")




    }

}