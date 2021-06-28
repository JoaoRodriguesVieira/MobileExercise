package com.example.mobileexercise.models

import java.io.Serializable

data class PhotoResponse (
    val photos: Photos,
    val stat : String
): Serializable