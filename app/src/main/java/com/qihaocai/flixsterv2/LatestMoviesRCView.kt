package com.qihaocai.flixsterv2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LatestMoviesRCView (
    private val Movies: List<Movies>,
    private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<LatestMoviesRCView.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_best_movies, parent, false)
        return MovieViewHolder(view)
    }

    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movies? = null
        val mTitle: TextView = mView.findViewById<View>(R.id.title) as TextView
        val mImage: ImageView = mView.findViewById<View>(R.id.movie_image) as ImageView
        val mDescription: TextView = mView.findViewById<View>(R.id.movie_description) as TextView
        val mRating: TextView = mView.findViewById<View>(R.id.rating) as TextView



        override fun toString(): String {
            return mTitle.toString() + "'" + mImage + "'" + mDescription + "'" + mRating.toString() + ""
        }




    }



    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val Mov = Movies[position]

        holder.mItem = Mov
        holder.mTitle.text = Mov.title

//        holder.mDescription.text = Mov.description
        //holder.mRating.text.toString() = Mov.rating


        holder.mView.setOnClickListener {
            holder.mItem?.let { Mov ->
                mListener?.onItemClick(Mov)
            }
        }


        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/" + Mov.imageUrl)
            .centerInside()
            .into(holder.mImage)
    }




    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */


    override fun getItemCount(): Int {
        return Movies.size
    }
}