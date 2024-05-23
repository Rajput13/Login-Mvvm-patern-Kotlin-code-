package com.example.apihit.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pserinfo_table")
data class PserInfo(
   @PrimaryKey @ColumnInfo(name ="username") val username: String,
   @ColumnInfo(name ="password") val password: String,
   @ColumnInfo(name ="message")  val message: String,
   @ColumnInfo(name ="auth")     val auth: Any,
   @ColumnInfo(name ="status")   val status: String,
   @ColumnInfo(name ="exp_date") val exp_date: Any?,
   @ColumnInfo(name ="is_trial") val is_trial: String,
   @ColumnInfo(name ="active_cons") val active_cons: String,
   @ColumnInfo(name ="created_at") val created_at: String,
   @ColumnInfo(name ="max_connections") val max_connections: String,
   @ColumnInfo(name ="allowed_output_formats") val allowed_output_formats: List<String>
)
@Entity(tableName = "serverinfo_table")
data class ServerInfo(
   @PrimaryKey @ColumnInfo(name ="url") val url: String,
   @ColumnInfo(name ="port") val port: String,
   @ColumnInfo(name="https_port") val https_port: String,
   @ColumnInfo(name="server_protocol") val server_protocol: String,
   @ColumnInfo(name="rtmp_port") val rtmp_port: String,
   @ColumnInfo(name="timezone") val timezone: String,
   @ColumnInfo(name="timestamp_now") val timestamp_now: Long,
   @ColumnInfo(name="time_now") val time_now: String
)
@Entity(tableName = "login_model_table")
data class LoginModel(
   @Embedded val user_info: PserInfo,
   @Embedded val server_info: ServerInfo
)


