package com.merricklabs.drogon.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import mu.KotlinLogging
import org.koin.standalone.KoinComponent

private val log = KotlinLogging.logger {}

class SnsHandlerLogic : RequestHandler<Map<String, Any>, Unit>, KoinComponent {

    override fun handleRequest(input: Map<String, Any>, context: Context) {
        log.info("Received request")
    }
}

