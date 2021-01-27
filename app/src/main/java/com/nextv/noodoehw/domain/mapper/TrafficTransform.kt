package com.nextv.noodoehw.domain.mapper

import com.nextv.noodoehw.domain.TrafficUI
import com.nextv.noodoehw.domain.data.Info

/**
 * Created by timhuang on 2021/1/27.
 * put logic of how to transform [Info] into [TrafficUI] here
 **/

fun Info.asUIdata(): TrafficUI {
    try {
        val title = chtmessage
                .takeIf { it.contains("/") && it.contains(":") }
                ?.run { substring(indexOf(" ",startIndex = 6)+1) } ?: chtmessage
        return TrafficUI(
                title = title,
                time = updatetime.substring(0,16),
                url = url?:""
        )
    }catch (e:Exception){
        //in case the response format of content change
        return TrafficUI(title = chtmessage,time = updatetime,url=url?:"")
    }
}