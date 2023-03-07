package com.example.needflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvDrakors: RecyclerView
    private val list = ArrayList<Drakor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDrakors = findViewById(R.id.rv_drakor)
        rvDrakors.setHasFixedSize(true)

        list.addAll(getListDrakors())
        showRecyclerList()

        val btn_about:Button = findViewById(R.id.about_page)
        btn_about.setOnClickListener(this)

    }

    private fun getListDrakors(): ArrayList<Drakor>{
        //retrieve data from String.xml
        val dataTitle = resources.getStringArray(R.array.drakorTitle)
        val dataReleaseYear = resources.getIntArray(R.array.releaseYear)
        val dataJumlahEpisode = resources.getStringArray(R.array.jumlahEpisode)
        val dataProductionStudio = resources.getStringArray(R.array.productionStudio)
        val dataPoster = resources.getStringArray(R.array.poster_picture)
        val dataGenre = resources.getStringArray(R.array.genre)
        val dataSinopsis = resources.getStringArray(R.array.sinopsis)
        val dataCast = resources.getStringArray(R.array.castName)
        val listPoster = resources.obtainTypedArray(R.array.drakor_poster)

        //declare new listDrakor
        val listDrakor = ArrayList<Drakor>()

        for (i in dataTitle.indices){
            //Assign value to each drakor object
            val drakor = Drakor(dataTitle[i], dataReleaseYear[i], dataJumlahEpisode[i],dataGenre[i], dataPoster[i],
                dataCast[i], dataProductionStudio[i], dataSinopsis[i], listPoster.getResourceId(i, -1) )
            listDrakor.add(drakor)
        }
        return listDrakor
    }

    private fun showRecyclerList(){
        rvDrakors.layoutManager = LinearLayoutManager(this)
        val listDrakorAdapter = ListDrakorAdapter(list)
        rvDrakors.adapter = listDrakorAdapter

        listDrakorAdapter.setOnItemClickCallback(object : ListDrakorAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Drakor) {
                //pakai intent untuk pindah ke halaman detail
                val moveToDetailDrakor = Intent(this@MainActivity, detail_drakor::class.java)
                moveToDetailDrakor.putExtra(detail_drakor.EXTRA_DRAKOR, data)
                startActivity(moveToDetailDrakor)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_list -> {
                rvDrakors.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvDrakors.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.about_page -> {
                val aboutIntent = Intent(this@MainActivity, about::class.java)
                startActivity(aboutIntent)
            }
        }
    }
}