package com.turk.footballrepository

import com.turk.dtos.serverObjects.FootballMatchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballMatchApiService {

    @GET("/v3/{path}")
     fun getFootballMatchesData(@Path("path") path:String): Call<List<FootballMatchResponse>>
}