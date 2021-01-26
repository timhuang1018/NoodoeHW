package com.nextv.noodoehw.helper

/**
 * Created by TimHuang on 2021/1/26.
 * use this class to let data binding object only be handled once
 */

open class EventWrapper<out T>(private val content:T){
    var hasBeenHandled =false
        private set

    fun getContentIfNotHandled():T?{
        return if (hasBeenHandled){
            null
        }else{
            hasBeenHandled = true
            content
        }
    }

    fun peekContent():T = content

    override fun toString(): String {
        return "event wrapper has content:${content.toString()}"
    }
}