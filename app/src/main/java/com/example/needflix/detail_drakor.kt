package com.example.needflix

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.needflix.databinding.ActivityDetailDrakorBinding

class detail_drakor : AppCompatActivity(), View.OnClickListener {
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
//            Glide.with(this@detail_drakor)
//                .load(drakor.poster)
//                .into(binding.drakorPoster)
            binding.drakorGenre.text = drakor.genre
            binding.drakorSinopsis.text = drakor.sinopsis
            binding.drakorTitle.text = drakor.title
            binding.drakorJumlahEpisode.text = drakor.jumlahEpisode
            binding.drakorProductionStudio.text = drakor.productionStudio
            binding.drakorReleaseYear.text = drakor.releaseYear.toString()
            binding.drakorCast.text = drakor.castName

        }

        binding.btnShare.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val drakor = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<Drakor>(EXTRA_DRAKOR, Drakor::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DRAKOR)
        }

        when(v.id){
            R.id.btn_share -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_TEXT,drakor.toString())
                shareIntent.type = "text/plain"

                val sendIntent = Intent.createChooser(shareIntent, "Share to")
                startActivity(sendIntent)
            }
        }
    }


}