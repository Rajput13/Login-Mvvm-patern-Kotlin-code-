package com.example.apihit.model.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apihit.database.UserRoomDatabase
import com.example.apihit.model.LiveCategoriesModel
import com.example.apihit.model.LoginModel
import com.example.apihit.model.LoginResponses
import com.example.apihit.network.BaseResponse
import com.example.apihit.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var userRepo = UserRepository()
    val _loginResponse: MutableLiveData<BaseResponse<LoginModel>> = MutableLiveData()
    val _liveCategoriesResponse: MutableLiveData<BaseResponse<LiveCategoriesModel>> = MutableLiveData()


    fun login(_username: String, _password: String) {

        _loginResponse.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                if (_username.toString().isEmpty() || _password.toString().isEmpty()) {
                    // Handle empty username or password
                    Log.e("Aaradhya", "fill your Credentials !")
                } else {


                  val loginResp =  userRepo.validateLogin(_username, _password)

                    if (loginResp?.code() == 200) {
                        _loginResponse.value =  BaseResponse.Success(loginResp.body())
                    } else {
                        _loginResponse.value = BaseResponse.Error(loginResp?.message())
                    }
                }
            } catch (e: Exception) {
                Log.e("Aaradhya", "Error" + e)
            }
        }
    }


    fun getLivecategories(_username: String,_password: String,context: Context){
        _loginResponse.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val loginModelDao = UserRoomDatabase.getUserInfo(context).personInfo()
                 val loginDetails : Flow<List<LoginModel>> =  loginModelDao.getUserDetails()
                val loginRepo = userRepo.getLiveCategories(_username,_password)
                if (loginRepo?.code() == 200){
                    _liveCategoriesResponse.value = BaseResponse.Success(loginRepo.body())
                }else {
                    _liveCategoriesResponse.value = BaseResponse.Error(loginRepo?.message())
                }
            }catch (e :Exception){
                Log.e("Aaradhya", "Error" + e)
            }
        }
    }

}