package com.example.mobileexercise.models

import java.io.Serializable

data class Photos (
    val page : Int,
    val pages : Int,
    val perpage : Int,
    val total : Long,
    val photo : List<Photo>
): Serializable