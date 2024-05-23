package com.example.apihit.database

import androidx.core.app.Person
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apihit.model.LoginModel
import com.example.apihit.model.PserInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
@Query("SELECT * FROM login_model_table ORDER BY username ASC")
fun getUserDetails(): Flow<List<LoginModel>>

@Insert(onConflict = OnConflictStrategy.IGNORE)
suspend fun insertPersonInfo(loginModel: LoginModel)

@Query("DELETE FROM login_model_table")
suspend fun deletePersonInfoAll()

}