package com.merricklabs.drogon

import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod

@Suppress("UNCHECKED_CAST")
open class DrogonIntegrationTestBase : KoinTest {

    // Workaround for Mockito in Kotlin.
    // See https://medium.com/@elye.project/befriending-kotlin-and-mockito-1c2e7b0ef791
    protected fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

    @BeforeMethod
    protected fun beforeMethod() {
        startKoin {
            modules(DrogonModule)
        }
        declareMock<DrogonConfig> {
            given(this.notifyMe.webhookUri).willReturn("foo")
        }
    }

    @AfterMethod
    protected fun afterMethod() {
        stopKoin()
    }
}