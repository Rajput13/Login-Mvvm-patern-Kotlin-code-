package com.example.apihit.mainactivity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.apihit.R
import com.example.apihit.databinding.ActivityDashBoardBinding
import com.example.apihit.model.LiveCategoriesModel
import com.example.apihit.model.viewmodel.LoginViewModel
import com.example.apihit.network.BaseResponse

class DashBoardActivity : AppCompatActivity() {
   private lateinit var dashBoardBinding :ActivityDashBoardBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashBoardBinding = DataBindingUtil.setContentView(this, R.layout.activity_dash_board)
        Toast.makeText(this@DashBoardActivity,"Welcome !!!!",Toast.LENGTH_SHORT).show()

        viewModel.getLivecategories("Reed1", "7063105152",this@DashBoardActivity)
        getLiveCategories()
    }
    fun getLiveCategories(){
        viewModel._liveCategoriesResponse.observe(this){
            when (it) {
                is BaseResponse.Error -> Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                is BaseResponse.Loading -> Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()

                is BaseResponse.Success -> {
                    it.data?.let {
                        it.categoryId
                        it.categoryName
                        it.parentId
                    }
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}