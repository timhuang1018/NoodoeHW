package com.nextv.noodoehw.domain

/**
 * Created by timhuang on 2021/1/26.
 **/

data class UserUI(
    val username:String,
    val phone:String?,
//    val createdAt:String,
//    val updatedAt:String,
    val objectId:String,
    val sessionToken:String
)