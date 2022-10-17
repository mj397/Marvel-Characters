package com.app.marvelcharacters

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Thumbnail : Serializable {
    @SerializedName("path")
    val path: String? = null

    @SerializedName("extension")
    val extension: String? = null
}
