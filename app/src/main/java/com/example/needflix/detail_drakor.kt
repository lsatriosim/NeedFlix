package com.example.needflix

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.needflix.databinding.ActivityDetailDrakorBinding

class detail_drakor : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDrakorBinding

    companion object {
        const val EXTRA_DRAKOR = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_drakor)
        binding = ActivityDetailDrakorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drakor = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<Drakor>(EXTRA_DRAKOR, Drakor::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DRAKOR)
        }

        if (drakor != null ){
            Glide.with(this@detail_drakor)
                .load(drakor.poster)
                .into(binding.drakorPoster)
            binding.drakorGenre.text = drakor.genre
            binding.drakorSinopsis.text = drakor.sinopsis
            binding.drakorTitle.text = drakor.title
            binding.drakorJumlahEpisode.text = drakor.jumlahEpisode
            binding.drakorProductionStudio.text = drakor.productionStudio
            binding.drakorReleaseYear.text = drakor.releaseYear.toString()
            binding.castNameOne.text = drakor.castName[0]
            binding.castNameTwo.text = drakor.castName[1]
            binding.castNameThree.text = drakor.castName[2]
            binding.castNameFour.text = drakor.castName[3]
            binding.castNameFive.text = drakor.castName[4]
            binding.photoCastOne.setImageResource(DrakorCast.castPhoto[drakor.castName[0]])
            binding.photoCastOne.setImageResource(DrakorCast.castPhoto[drakor.castName[1]])
            binding.photoCastOne.setImageResource(DrakorCast.castPhoto[drakor.castName[2]])
            binding.photoCastOne.setImageResource(DrakorCast.castPhoto[drakor.castName[3]])
            binding.photoCastOne.setImageResource(DrakorCast.castPhoto[drakor.castName[4]])

        }
    }
}