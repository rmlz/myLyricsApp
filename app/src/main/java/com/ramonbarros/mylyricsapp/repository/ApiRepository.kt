package com.ramonbarros.mylyricsapp.repository

import com.google.gson.GsonBuilder
import com.ramonbarros.mylyricsapp.domain.Character
import com.ramonbarros.mylyricsapp.repository.dto.Page
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RickMortyApi {

    @GET("character")
    @Headers("Content-Type: application/json")
    suspend fun getCharacter(@Query("page") page: Int): Page

/*    @GET("location")
    suspend fun getLocation()

    @GET("episode")
    suspend fun getEpisodes()*/
}

class ApiRepository {

    private val conector: Retrofit

    init {
        val gsonConfig = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
        conector = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gsonConfig))
            .build()
    }

    suspend fun callAPI(): List<Character> {
        val service = conector.create(RickMortyApi::class.java)
        val characterList = service.getCharacter(1).results

        return characterList.map { dto ->
            Character(dto.name, dto.gender, dto.type, dto.image)
        }


    }
}