
package com.example.mobileexercise

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Constants {

    const val APP_ID: String = "7bdb03d29144dbbabc9c71fd173ac356"
    const val BASE_URL : String = "https://api.flickr.com/"
    const val METHOD1 : String = "flickr.photos.getSizes"
    const val METHOD2 : String = "flickr.photos.search"
    const val FORMAT : String = "json"
    const val NO_JSON_CALL_BACK : Int = 1
    const val TAGS : String = "bird"
    const val PAGE : Int = 1

    const val PREFERENCE_NAME1 : String = "FlickrGetSizes"
    const val PREFERENCE_NAME2 : String = "FlickrPhotosSearch"

    const val FLICKR_GET_SIZES_DATA : String = "flickr_get_sizes"
    const val FLICKR_PHOTOS_SEARCH_DATA : String = "flickr_photos_search"

    fun isNetworkAvailable(context: Context) : Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when{
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

        }else{
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
}
