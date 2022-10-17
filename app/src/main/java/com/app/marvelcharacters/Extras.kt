package com.app.marvelcharacters

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Extras : Serializable {
    @SerializedName("available")
    val available: String? = null

    @SerializedName("items")
    val items: ArrayList<Items>? = null
}
