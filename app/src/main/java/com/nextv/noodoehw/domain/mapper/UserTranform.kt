package com.nextv.noodoehw.domain.mapper

import com.nextv.noodoehw.domain.UserUI
import com.nextv.noodoehw.domain.data.UserRemote

/**
 * Created by timhuang on 2021/1/26.
 **/

fun UserRemote.asUser(): UserUI {
    return UserUI(
        username =username,
        timezone = timezone,
//        objectId=objectId,
//        sessionToken=sessionToken
    )
}
