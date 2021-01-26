package com.nextv.noodoehw.domain.mapper

import com.nextv.noodoehw.domain.User
import com.nextv.noodoehw.domain.data.UserRemote

/**
 * Created by timhuang on 2021/1/26.
 **/

fun UserRemote.asUser(): User {
    return User(
        username =username,
        phone= phone,
        objectId=objectId,
        sessionToken=sessionToken
    )
}
