package com.merricklabs.drogon.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

class DrogonObjectMapper : ObjectMapper() {
    init {
        this.registerModule(KotlinModule())
    }
}