package com.example.apihit.authactivity


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.apihit.R
import com.example.apihit.database.UserRoomDatabase
import com.example.apihit.databinding.ActivityLoginBinding
import com.example.apihit.mainactivity.DashBoardActivity
import com.example.apihit.model.LoginModel
import com.example.apihit.model.LoginResponses
import com.example.apihit.model.viewmodel.LoginViewModel
import com.example.apihit.network.BaseResponse
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {
    private lateinit var loginActivityBinding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginActivityBinding.userDetails = viewModel
        submit()
        loginObserver()

    }

    fun submit() {
        loginActivityBinding.rlBtSubmit.setOnClickListener {

            loginActivityBinding.rlEmail.setText("Reed1")
            loginActivityBinding.rlPassword.setText("7063105152")
            if (!loginActivityBinding.rlEmail.text.toString()
                    .isEmpty() || !loginActivityBinding.rlPassword.text.toString().isEmpty()
            ) {
                var username: String = loginActivityBinding.rlEmail.text.toString()
                var password: String = loginActivityBinding.rlPassword.text.toString()
                viewModel.login(username, password)
            }
        }
    }

    fun loginObserver() {
        viewModel._loginResponse.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }

                is BaseResponse.Error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }

                is BaseResponse.Success -> {
                    lifecycleScope.launch {
                        loginDetails(it)

                    }

                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun loginDetails(it: BaseResponse.Success<LoginModel>) {
        val logindetails: ArrayList<LoginResponses> = ArrayList()
        var userName: String? = null
        var password: String? = null
        var auth: Any? = null
        var message: String? = null
        var status: String? = null
        var expiration_data: Any? = null
        var portName: String? = null
        var httpsPort: String? = null
        var serverProtocol: String? = null
        var rtmpPort: String? = null
        var timeZone: String? = null
        var timestampNow: Long? = null
        var timeNow: String? = null

        it.data?.user_info?.apply {
            userName = username
            password = password
            auth = auth
            expiration_data = exp_date
            status = status
            message = message
        }
        it.data?.server_info?.apply {
            portName = port
            httpsPort = https_port
            serverProtocol = server_protocol
            rtmpPort = rtmp_port
            timeZone = timezone
            timestampNow = timestamp_now
            timeNow = time_now

        }
        val loginModel = it.data?.let { it1 -> LoginModel(it.data.user_info, it1.server_info) }
        val loginModelDao = UserRoomDatabase.getUserInfo(this).personInfo()
        if (loginModel != null) {
            loginModelDao.insertPersonInfo(loginModel)
        }

        logindetails.add(
            LoginResponses(
                userName,
                password,
                message,
                status,
                auth,
                expiration_data
            )
        )


        val intent = Intent(this@LoginActivity, DashBoardActivity::class.java)
        startActivity(intent)
        finish()
    }

}
