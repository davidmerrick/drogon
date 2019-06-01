package com.merricklabs.drogon.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.merricklabs.drogon.DrogonConfig
import com.merricklabs.drogon.external.notifyme.NotifyMePayload
import com.merricklabs.drogon.util.DrogonObjectMapper
import mu.KotlinLogging
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

private val log = KotlinLogging.logger {}

class SnsHandlerLogic : RequestHandler<Map<String, Any>, Unit>, KoinComponent {
    private val config: DrogonConfig  by inject()
    private val mapper: DrogonObjectMapper  by inject()

    override fun handleRequest(input: Map<String, Any>, context: Context) {
        log.info("Received request")
        log.info(mapper.writeValueAsString(input))


        // Todo: Create a schema. Just using this for doorbell notifications, for now
        val payload = NotifyMePayload(
                notification = "Buzzed someone in",
                title = "Buzzed someone in",
                accessCode = config.notifyMe.accessCode
        )

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

