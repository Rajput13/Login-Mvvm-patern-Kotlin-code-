package com.example.apihit.network

import com.example.apihit.model.LiveCategoriesModel
import com.example.apihit.model.LoginModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UserApi {
    @GET("player_api.php")
   suspend fun validateLogin(
        @Header("Content-Type") contentType: String?,
        @Query("username") username: String?,
        @Query("password") password: String?): Response<LoginModel?>?

   companion object{
   fun getApi() : UserApi? {
        return ApiClient.client?.create(UserApi ::class.java)
    }
   }


    @GET("player_api.php")
    suspend fun getLiveCategories(
        @Header("Content-Type") contentType: String?,
        @Query("username") username: String?,
        @Query("password") password: String?,
        @Query("action") getlivecategories:String?): Response<LiveCategoriesModel?>?




}