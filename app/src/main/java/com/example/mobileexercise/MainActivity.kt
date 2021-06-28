package com.example.mobileexercise


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mobileexercise.models.*
import com.example.mobileexercise.network.FlickrPhotosGetSize
import com.example.mobileexercise.network.FlickrPhotosSearch
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

private var photoIncreaser : Int = 0

private var sizeList : SizesResponse? = null
private var photoList : PhotoResponse? = null

private var list =  ArrayList<String>()

private var adapater = SizesAdapter(list)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view!!.layoutManager = GridLayoutManager(this, 2)
        recycler_view!!.adapter = adapater

        getPhotoSearch()
    }

    private fun getPhotoSearch() {
        if (Constants.isNetworkAvailable(this)) {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val photosGetSearch: FlickrPhotosSearch =
                retrofit.create<FlickrPhotosSearch>(FlickrPhotosSearch::class.java)

            val listCall: Call<PhotoResponse> = photosGetSearch.getSearch(
                Constants.METHOD2,
                Constants.APP_ID,
                Constants.TAGS,
                Constants.PAGE,
                Constants.FORMAT,
                Constants.NO_JSON_CALL_BACK
            )

            listCall.enqueue(object : Callback<PhotoResponse> {
                override fun onResponse(
                    call: Call<PhotoResponse>,
                    response: Response<PhotoResponse>
                ) {
                    if (response!!.isSuccessful) {
                        photoList = response.body()!!
                        getSizesSearch()
                        Log.i("Response Result", "$photoList")
                    } else {
                        val rc = response.code()
                        when (rc) {
                            400 -> {
                                Log.e("Error 400", "Bad Connection")
                            }
                            404 -> {
                                Log.e("Error 400", "Not Found")
                            }
                            else -> {
                                Log.e("Error", "Generic Error")
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                    Log.e("Errorrrrr", t!!.message.toString())
                }
            })
        } else {
            Toast.makeText(
                this@MainActivity,
                "No internet connection available.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getSizesSearch() {
        if (Constants.isNetworkAvailable(this)) {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val photosGetSize: FlickrPhotosGetSize =
                retrofit.create<FlickrPhotosGetSize>(FlickrPhotosGetSize::class.java)

            for (photo in photoList!!.photos.photo){

                val listCall: Call<SizesResponse> = photosGetSize.getImages(
                    Constants.METHOD1,
                    Constants.APP_ID,
                    photoList!!.photos.photo[photoIncreaser].id,
                    Constants.FORMAT,
                    Constants.NO_JSON_CALL_BACK
                )
                
                photoIncreaser++

                listCall.enqueue(object : Callback<SizesResponse> {
                    override fun onResponse(
                        call: Call<SizesResponse>,
                        response: Response<SizesResponse>
                    ) {
                        if (response.isSuccessful) {
                            sizeList = response.body()!!
                            list.add(sizeList!!.sizes.size[1].source)
                            adapater.notifyDataSetChanged()
                        } else {
                            val rc = response.code()
                            when (rc) {
                                400 -> {
                                    Log.e("Error 400", "Bad Connection")
                                }
                                404 -> {
                                    Log.e("Error 400", "Not Found")
                                }
                                else -> {
                                    Log.e("Error", "Generic Error")
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<SizesResponse>, t: Throwable?) {
                        Log.e("Errorrrrr", t!!.message.toString())
                    }

                })
            }

        } else {
            Toast.makeText(
                this@MainActivity,
                "No internet connection available.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun getBitmapFromURL(src: String?): Bitmap? {
        try {
            Log.e("src", src!!)
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input: InputStream = connection.getInputStream()
            val myBitmap = BitmapFactory.decodeStream(input)
            Log.e("Bitmap", "returned")
            return myBitmap
        } catch (e : Exception) {
            e.printStackTrace()
            return null
        }
    }
}



