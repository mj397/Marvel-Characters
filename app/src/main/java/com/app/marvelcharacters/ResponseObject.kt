package com.app.marvelcharacters

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ResponseObject : Serializable {
    @SerializedName("code")
    val code: String? = null

    @SerializedName("status")
    val status: String? = null

    @SerializedName("copyright")
    val copyRight: String? = null

    @SerializedName("data")
    val data: DataModel? = null
}
