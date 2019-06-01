package com.merricklabs.drogon

import org.koin.standalone.KoinComponent

class DrogonConfig : KoinComponent {
    val notifyMe = NotifyMe()

    inner class NotifyMe {
        val webhookUri = "https://api.notifymyecho.com/v1/NotifyMe"
        val accessCode = System.getenv("NOTIFYME_KEY")
    }
}