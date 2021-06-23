package com.kerjapraktek.gunungindonesia.ui.main

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kerjapraktek.gunungindonesia.R
import com.kerjapraktek.gunungindonesia.databinding.ActivityMainBinding
import com.kerjapraktek.gunungindonesia.model.Menu
import com.kerjapraktek.gunungindonesia.ui.main.adapter.MenuListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvMenu: RecyclerView
    private lateinit var menuListAdapter: MenuListAdapter
    private var list: ArrayList<Menu> = arrayListOf()
    private lateinit var dataTitle: Array<String>
    private lateinit var dataIc: TypedArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        showMenu()
    }

    private fun showMenu() {
        rvMenu=binding.rvMainMenu
        rvMenu.setHasFixedSize(true)
        rvMenu.layoutManager = LinearLayoutManager(this)
        menuListAdapter = MenuListAdapter(list)
        list.addAll(getListMenu())
        rvMenu.adapter = menuListAdapter
    }

    private fun getListMenu(): ArrayList<Menu> {
        val listMenu= ArrayList<Menu>()
        dataTitle = resources.getStringArray(R.array.data_title)
        dataIc = resources.obtainTypedArray(R.array.data_ic)
        for(position in dataTitle.indices){
            val menu=Menu(
                dataTitle[position],
                dataIc.getResourceId(position, -1)
            )
            listMenu.add(menu)
        }
        return listMenu
    }
}