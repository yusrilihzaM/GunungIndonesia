package com.kerjapraktek.gunungindonesia.ui.main

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kerjapraktek.gunungindonesia.R
import com.kerjapraktek.gunungindonesia.databinding.ActivityMainBinding
import com.kerjapraktek.gunungindonesia.model.Menu
import com.kerjapraktek.gunungindonesia.ui.jabar.JabarActivity
import com.kerjapraktek.gunungindonesia.ui.jateng.JatengActivity
import com.kerjapraktek.gunungindonesia.ui.jatim.JatimActivity
import com.kerjapraktek.gunungindonesia.ui.luarjawa.LuarJawaActivity
import com.kerjapraktek.gunungindonesia.ui.main.adapter.MenuListAdapter
import com.kerjapraktek.gunungindonesia.ui.peralatan.PeralatanActivity

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

        binding.floatingActionButton.setOnClickListener {
            Toast.makeText(this@MainActivity, getString(R.string.peralatan), Toast.LENGTH_SHORT).show()
            intent= Intent(this@MainActivity, PeralatanActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showMenu() {
        rvMenu=binding.rvMainMenu
        rvMenu.setHasFixedSize(true)
        rvMenu.layoutManager = LinearLayoutManager(this)
        menuListAdapter = MenuListAdapter(list)
        list.addAll(getListMenu())
        rvMenu.adapter = menuListAdapter
        menuListAdapter.setOnItemClickCallback(object :MenuListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Menu) {
                val intent: Intent
                when(data.title){
                    getString(R.string.jawa_timur)->{
                        Toast.makeText(this@MainActivity, getString(R.string.jawa_timur), Toast.LENGTH_SHORT).show()
                        intent= Intent(this@MainActivity, JatimActivity::class.java)
                        startActivity(intent)
                    }
                    getString(R.string.jawa_barat)->{
                        Toast.makeText(this@MainActivity, getString(R.string.jawa_barat), Toast.LENGTH_SHORT).show()
                        intent= Intent(this@MainActivity, JabarActivity::class.java)
                        startActivity(intent)
                    }
                    getString(R.string.jawa_tengah)->{
                        Toast.makeText(this@MainActivity, getString(R.string.jawa_tengah), Toast.LENGTH_SHORT).show()
                        intent= Intent(this@MainActivity, JatengActivity::class.java)
                        startActivity(intent)
                    }
                    getString(R.string.luar_jawa)->{
                        Toast.makeText(this@MainActivity, getString(R.string.luar_jawa), Toast.LENGTH_SHORT).show()
                        intent= Intent(this@MainActivity, LuarJawaActivity::class.java)
                        startActivity(intent)
                    }

                }

            }
        })
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