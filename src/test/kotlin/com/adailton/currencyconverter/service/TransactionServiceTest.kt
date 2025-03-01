package com.adailton.currencyconverter.service

import com.adailton.currencyconverter.dto.TransactionRequest
import com.adailton.currencyconverter.exception.ResourceNotFoundException
import com.adailton.currencyconverter.model.Transaction
import com.adailton.currencyconverter.repository.TransactionRepository
import com.adailton.currencyconverter.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

class TransactionServiceTest {
    private val transactionRepository = mock(TransactionRepository::class.java)
    private val exchangeRateService = mock(ExchangeRateService::class.java)
    private val userRepository = mock(UserRepository::class.java)

    private val transactionService = TransactionService(transactionRepository, exchangeRateService, userRepository)

    @Test
    fun `should create a transaction successfully`() {

        val userId = 1L
        val conversionRate = BigDecimal("0.169893")
        val request = TransactionRequest(userId, "BRL", BigDecimal("100.00"), "USD")

        whenever(userRepository.findById(userId)).thenReturn(Optional.of(mock()))

        whenever(exchangeRateService.getExchangeRate("BRL", "USD")).thenReturn(conversionRate)

        val expectedTransaction = Transaction(
            id = 1L,
            userId = userId,
            fromCurrency = "BRL",
            fromAmount = BigDecimal("100.00"),
            toCurrency = "USD",
            toAmount = BigDecimal("16.99"),
            conversionRate = conversionRate,
            timestamp = LocalDateTime.now()
        )

        whenever(transactionRepository.save(any())).thenReturn(expectedTransaction)

        val result = transactionService.createTransaction(request)

        assertNotNull(result)
        assertEquals("BRL", result.fromCurrency)
        assertEquals("USD", result.toCurrency)
        assertEquals(BigDecimal("16.99"), result.toAmount)
    }

    @Test
    fun `should throw error if user does not exist`() {

        val userId = 999L
        val request = TransactionRequest(userId, "BRL", BigDecimal("100.00"), "USD")

        whenever(userRepository.findById(userId)).thenReturn(Optional.empty())

        val exception = assertThrows<ResourceNotFoundException> {
            transactionService.createTransaction(request)
        }

        assertEquals("User with ID 999 not found.", exception.message)
    }

}