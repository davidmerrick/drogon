package com.merricklabs.drogon.external.notifyme

import com.fasterxml.jackson.databind.ObjectMapper
import com.merricklabs.drogon.DrogonConfig
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.koin.core.KoinComponent
import org.koin.core.inject

class NotifyMeHelper : KoinComponent {
    private val config: DrogonConfig by inject()
    private val mapper: ObjectMapper by inject()

    fun postNotification(payload: NotifyMePayload) {
        val okHttpClient = OkHttpClient()
        val json = MediaType.get("application/json; charset=utf-8")
        val body = RequestBody.create(json, mapper.writeValueAsString(payload))
        val request = Request.Builder()
                .url(config.notifyMe.webhookUri)
                .post(body)
                .build()
        okHttpClient.newCall(request).execute()
    }
}