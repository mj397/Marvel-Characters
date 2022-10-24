package com.app.marvelcharacters.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class DataModel : Serializable {
    @SerializedName("results")
    val results: ArrayList<Results>? = null
}
