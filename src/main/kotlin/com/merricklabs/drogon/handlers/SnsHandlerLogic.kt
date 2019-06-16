package com.merricklabs.drogon.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.fasterxml.jackson.databind.ObjectMapper
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

class SnsHandlerLogic : RequestHandler<SNSEvent, Unit>, KoinComponent {
    private val config: DrogonConfig by inject()
    private val mapper: ObjectMapper by inject()

    override fun handleRequest(input: SNSEvent, context: Context) {
        log.info("Received request")
        log.info(mapper.writeValueAsString(input))

        val payload = NotifyMePayload(
                notification = input.records[0].sns.message,
                title = input.records[0].sns.message,
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

