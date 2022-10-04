package com.qihaocai.flixsterv2


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


private lateinit var mediaImageView: ImageView
private lateinit var titleTextView: TextView
private lateinit var ratingTextView: TextView
private lateinit var movieTextView: TextView

class DetailAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_act)

        mediaImageView = findViewById(R.id.movie_image)
        titleTextView = findViewById(R.id.title)
        ratingTextView = findViewById(R.id.rating)
        movieTextView = findViewById(R.id.movie_description)

        var title = intent.getStringExtra("title")
        var desc = intent.getStringExtra("desc")
        var rating = intent.getStringExtra("rating")
        var imageUrl = intent.getStringExtra("img")

        titleTextView.text = title
        ratingTextView.text = rating
        movieTextView.text = desc



        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + imageUrl)
            .centerInside()
            .into(mediaImageView)


    }

}


