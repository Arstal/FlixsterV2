package com.qihaocai.flixsterv2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers


private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class LatestMoviesFragment : Fragment(), OnListFragmentInteractionListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_best_movies_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        updateAdapter(progressBar, recyclerView)



        return view
    }
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here

        val newClient = AsyncHttpClient();
        val params = RequestParams()
        params["api-key"] = API_KEY

        // Using the client, perform the HTTP request

        newClient[
                "https://api.themoviedb.org/3/movie/top_rated?api_key=34e61e3ddd6944220f1e09bcfb1b726d",
                params,
                object : JsonHttpResponseHandler()

                //Uncomment me once you complete the above sections!
                {
                    /*
                     * The onSuccess function gets called when
                     * HTTP response status is "200 OK"
                     */
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        //TODO - Parse JSON into Models

                        val resultsJSON = json.jsonObject.get("results").toString()

                        //val booksRawJSON : String = resultsJSON.get("movies").toString()

                        val gson = Gson()

                        val arrayBookType = object : TypeToken<List<Movies>>() {}.type

                        val models : List<Movies> = gson.fromJson(resultsJSON, arrayBookType)

                        recyclerView.adapter = LatestMoviesRCView(models, this@LatestMoviesFragment)


                        // Look for this in Logcat:
                        Log.d("LatestMoviesFragment", "response successful")
                    }

                    /*
                     * The onFailure function gets called when
                     * HTTP response status is "4XX" (eg. 401, 403, 404)
                     */
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("LatestMoviesFragment", errorResponse)
                        }
                    }
                }]


    }
    override fun onItemClick(item: Movies) {
        //Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
        var title = item.title
        var desc = item.description
        var img = item.imageUrl
        var rating = item.rating.toString()

        val intent = Intent(context, DetailAct::class.java)
        intent.putExtra("title", title)
        intent.putExtra("desc", desc)
        intent.putExtra("img", img)
        intent.putExtra("rating", rating)
        startActivity(intent)

    }
}


