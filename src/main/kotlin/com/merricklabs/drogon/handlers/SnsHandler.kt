package com.merricklabs.drogon.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.merricklabs.drogon.DrogonModule
import org.koin.standalone.StandAloneContext

class SnsHandler : RequestHandler<SNSEvent, Unit> {
    private val logic: SnsHandlerLogic

    init {
        StandAloneContext.startKoin(listOf(DrogonModule))
        logic = SnsHandlerLogic()
    }

    override fun handleRequest(input: SNSEvent, context: Context) {
        return logic.handleRequest(input, context)
    }
}