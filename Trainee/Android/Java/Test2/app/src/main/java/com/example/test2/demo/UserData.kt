package com.example.test2.demo

import java.io.Serializable

data class UserData(val imgProfile: Int, val userName: String, val userLastChat: String, val userTime: String, val msgCount: Int):
    Serializable {
}
