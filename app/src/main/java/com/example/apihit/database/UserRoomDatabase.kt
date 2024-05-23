package com.example.apihit.database

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apihit.model.LoginModel
import com.example.apihit.model.PserInfo

@Database(entities = arrayOf(LoginModel :: class), version = 1, exportSchema = false)
abstract class UserRoomDatabase : RoomDatabase() {
abstract fun personInfo() : UserDAO
companion object{
    @Volatile
    private var  INSTANCE :UserRoomDatabase? = null

    fun getUserInfo (context : Context):UserRoomDatabase{
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                UserRoomDatabase::class.java,
                "word_database"
            ).build()
            INSTANCE = instance
            // return instance
            instance
        }
    }
}
}