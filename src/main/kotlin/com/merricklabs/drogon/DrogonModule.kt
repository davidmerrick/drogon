package com.merricklabs.drogon

import com.fasterxml.jackson.databind.ObjectMapper
import com.merricklabs.drogon.handlers.SnsHandlerLogic
import com.merricklabs.drogon.util.DrogonObjectMapper
import org.koin.dsl.module.module

val DrogonModule = module {
    single { SnsHandlerLogic() }
    single { DrogonConfig() }
    single { DrogonObjectMapper() as ObjectMapper }
}