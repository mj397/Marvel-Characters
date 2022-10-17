package com.app.marvelcharacters

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class DataModel : Serializable {
    @SerializedName("results")
    val results: ArrayList<Results>? = null
}
