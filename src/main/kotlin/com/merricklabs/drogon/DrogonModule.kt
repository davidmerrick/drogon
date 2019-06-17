package com.merricklabs.drogon

import com.fasterxml.jackson.databind.ObjectMapper
import com.merricklabs.drogon.external.notifyme.NotifyMeHelper
import com.merricklabs.drogon.handlers.SnsHandlerLogic
import com.merricklabs.drogon.util.DrogonObjectMapper
import org.koin.dsl.module

val DrogonModule = module {
    single { DrogonObjectMapper() as ObjectMapper }
    single { SnsHandlerLogic() }
    single { DrogonConfig() }
    single { NotifyMeHelper() }
}