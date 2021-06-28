package com.example.mobileexercise.models

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileexercise.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_row.view.*

class SizesAdapter (private val sizes : ArrayList<String>) : RecyclerView.Adapter<SizesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val size = sizes[position]
        Log.i("Size", "$size")
        Picasso.get().load(size).into(holder.imgView)
    }

    override fun getItemCount() : Int{
        return sizes.size
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val imgView : ImageView = view.imgView
    }
}