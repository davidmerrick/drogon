package com.merricklabs.drogon.external.notifyme

data class NotifyMePayload(
        val notification: String,
        val accessCode: String,
        val title: String
)