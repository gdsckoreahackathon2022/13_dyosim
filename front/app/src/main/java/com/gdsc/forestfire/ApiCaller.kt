package com.gdsc.forestfire

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.Exception
import kotlin.concurrent.thread

class ApiCaller {
    fun getAllForestFireData(code: Int): List<FireInfo> {
        val domain = "https://dyosim.herokuapp.com"
        val url = "${domain}/forestfire?code=${code}"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        try {
            val response = client.newCall(request).execute()
            val body = response.body()?.string() ?: ""

            val res = Gson().fromJson<ForestFireResponse>(
                body,
                ForestFireResponse::class.java
            )

            return res.data
        }
        catch (err: Exception) {
            err.printStackTrace()

            return emptyList<FireInfo>()
        }

    }
}