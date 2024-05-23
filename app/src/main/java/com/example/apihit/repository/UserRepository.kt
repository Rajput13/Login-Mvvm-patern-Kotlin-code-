package com.example.apihit.repository

import android.util.Log
import com.example.apihit.common.AppConst
import com.example.apihit.database.UserDAO
import com.example.apihit.model.LiveCategoriesModel
import com.example.apihit.model.LoginModel
import com.example.apihit.model.PserInfo
import com.example.apihit.network.UserApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class UserRepository() {

    suspend fun validateLogin(username :String,password :String) : Response<LoginModel?>? {
        val response = UserApi.getApi()?.validateLogin(AppConst.CONTENT_TYPE, username,password)
    Log.e(  "Aaradhya","Response code: ${response?.code()}")
        Log.e(  "Aaradhya", "Response body: ${response?.body()}")
        return  response
    }


    suspend fun getLiveCategories(username :String,password :String): Response<LiveCategoriesModel?>? {
        val liveCatResponse = UserApi.getApi()?.getLiveCategories(AppConst.CONTENT_TYPE,username,password,AppConst.ACTION_GET_LIVE_CATEGORIES)
        Log.e(  "Aaradhya","Response code: ${liveCatResponse?.code()}")
        Log.e(  "Aaradhya", "Response body: ${liveCatResponse?.body()}")
        return liveCatResponse
    }

}