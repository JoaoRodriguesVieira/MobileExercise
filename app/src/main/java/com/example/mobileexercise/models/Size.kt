package com.example.mobileexercise.models

import java.io.Serializable

data class Size (
    val label : String,
    val width : Int,
    val height : Int,
    val source : String,
    val url : String,
    val media : String
): Serializable