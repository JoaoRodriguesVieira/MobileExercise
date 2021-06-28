package com.example.mobileexercise.models

import java.io.Serializable

data class Sizes (
    val canblog : Int,
    val canprint : Int,
    val candownload : Int,
    val size :List<Size>
): Serializable