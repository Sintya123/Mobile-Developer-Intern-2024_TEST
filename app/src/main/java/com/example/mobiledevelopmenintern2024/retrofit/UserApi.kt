package com.example.mobiledevelopmenintern2024.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("users")
    fun getUsers(
        @Query("page") page: Int?,
    ):Call<UserResponse>
}