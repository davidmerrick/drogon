package com.merricklabs.drogon

import com.merricklabs.drogon.handlers.SnsHandlerLogic
import org.koin.dsl.module.module

val DrogonModule = module {
    single { SnsHandlerLogic() }
}