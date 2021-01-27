package com.nextv.noodoehw.helper

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

/**
 * Created by timhuang on 2021/1/26.
 **/

fun Context.toast(message:CharSequence?): Toast = Toast.makeText(this,message, Toast.LENGTH_SHORT).apply {
    setGravity(Gravity.CENTER_VERTICAL,0,0)
    show()
}

fun Context.toastLong(message:CharSequence?): Toast = Toast.makeText(this,message, Toast.LENGTH_LONG).apply {
    setGravity(Gravity.CENTER_VERTICAL,0,0)
    show()
}

fun Fragment.toast(message:CharSequence?) = activity?.toast(message)


val JSON : MediaType = "application/json; charset=utf-8".toMediaType()
fun createJsonRequestBody(vararg params: Pair<String,Any>) =
        JSONObject(mapOf(*params)).toString().toRequestBody(JSON)
