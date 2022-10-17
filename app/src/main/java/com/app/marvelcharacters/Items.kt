package com.app.marvelcharacters

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Items : Serializable {
    @SerializedName("name")
    val name: String? = null

    @SerializedName("resourceURI")
    val resourceURI: String? = null
}
