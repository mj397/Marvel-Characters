package com.app.marvelcharacters.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class Results : Serializable {
    @SerializedName("resourceURI")
    val resourceURI: String? = null

    @SerializedName("id")
    val id: String? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null

    @SerializedName("comics")
    val comics: Extras? = null

    @SerializedName("series")
    val series: Extras? = null

    @SerializedName("stories")
    val stories: Extras? = null

    @SerializedName("events")
    val events: Extras? = null
}
