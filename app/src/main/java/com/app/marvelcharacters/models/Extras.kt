package com.app.marvelcharacters.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class Extras : Serializable {
    @SerializedName("available")
    val available: String? = null
    @SerializedName("items")
    val items: ArrayList<Items>? = null
}
