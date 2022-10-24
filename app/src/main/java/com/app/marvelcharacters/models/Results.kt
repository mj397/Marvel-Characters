package com.app.marvelcharacters.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity (tableName = "characters")
class Results : Serializable {


    @PrimaryKey(autoGenerate = true)
    var idPrimary: Long? = null

    @SerializedName("resourceURI")
    var resourceURI: String? = null

    @SerializedName("id")
    var characterId: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("thumbnail")
    var thumbnail: Thumbnail? = null

    @SerializedName("comics")
    var comics: Extras ? = null

    @SerializedName("series")
    var series: Extras? = null

    @SerializedName("stories")
    var stories: Extras? = null

    @SerializedName("events")
    var events: Extras? = null
}
