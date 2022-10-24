package com.app.marvelcharacters.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class Items : Serializable {
    @SerializedName("name")
    val name: String? = null

    @SerializedName("resourceURI")
    val resourceURI: String? = null
}
