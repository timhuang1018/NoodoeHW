package com.nextv.noodoehw.domain.data

/**
 * Created by timhuang on 2021/1/25.
 **/
/*
{
  "username": "cooldude6",
  "phone": "415-392-0202",
  "createdAt": "2011-11-07T20:58:34.448Z",
  "updatedAt": "2011-11-07T20:58:34.448Z",
  "objectId": "g7y9tkhB7O",
  "sessionToken": "r:pnktnjyb996sj4p156gjtp4im"
}
 */
data class UserRemote(
    val username:String,
    val phone:String?,
    val createdAt:String,
    val updatedAt:String,
    val objectId:String,
    val sessionToken:String,
    val timezone:Int,
)

data class UpdateResponse(val updatedAt:String)

data class InfoResponse(val updateTime:String,val News:List<Info>)
data class Info(
    val chtmessage:String,
    val engmessage:String,
    val starttime:String,
    val endtime:String,
    val updatetime:String,
    val content:String,
    val url:String?
)


