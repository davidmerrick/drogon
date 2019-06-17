package com.merricklabs.drogon.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.merricklabs.drogon.DrogonConfig
import com.merricklabs.drogon.external.notifyme.NotifyMeHelper
import com.merricklabs.drogon.external.notifyme.NotifyMePayload
import org.koin.core.KoinComponent
import org.koin.core.inject

class SnsHandlerLogic : RequestHandler<SNSEvent, Unit>, KoinComponent {
    private val config: DrogonConfig by inject()
    private val helper: NotifyMeHelper by inject()

    override fun handleRequest(input: SNSEvent, context: Context) {
        val payload = NotifyMePayload(
                notification = input.records[0].sns.message,
                title = input.records[0].sns.message,
                accessCode = config.notifyMe.accessCode
        )

        helper.postNotification(payload)
    }
}

