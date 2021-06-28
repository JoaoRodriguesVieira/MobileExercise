package com.example.mobileexercise.models

import java.io.Serializable

data class SizesResponse(
    val sizes: Sizes,
    val stat : String
):Serializable