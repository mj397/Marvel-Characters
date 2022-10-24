package com.app.marvelcharacters.models

import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
@TypeConverters(ObjectConverter::class)
class Thumbnail : Serializable {
    @SerializedName("path")
    val path: String? = null

    @SerializedName("extension")
    val extension: String? = null
}
