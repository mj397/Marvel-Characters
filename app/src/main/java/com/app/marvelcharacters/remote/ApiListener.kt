package com.app.marvelcharacters.remote

import com.app.marvelcharacters.models.ResponseObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiListener {

    @GET("v1/public/characters")
    fun getCharacters(
        @Query("apikey") apiKey: String?,
        @Query("ts") ts: String?,
        @Query("hash") hash: String?,
        @Query("limit") limit: Int
    ): Call<ResponseObject?>?


}