package com.example.mobileexercise.models

import java.io.Serializable

data class Photo (
    val id : String,
    val owner : String,
    val secret : String,
    val server : String,
    val farm : Int,
    val title : String,
    val ispublic : Int,
    val isfriend : Int,
    val isfamily: Int
): Serializable