package com.adailton.currencyconverter.controller

import com.adailton.currencyconverter.service.ExchangeRateService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockitoBean
    private lateinit var exchangeRateService: ExchangeRateService

    @BeforeEach
    fun setup() {
        Mockito.`when`(exchangeRateService.getExchangeRate("BRL", "USD", "fake-key"))
            .thenReturn(BigDecimal("0.169893"))
    }

    @Test
    fun `should create a transaction successfully`() {

        val userRequest = mapOf("name" to "Test User", "email" to "test@example.com")

        val userResponse = mockMvc.perform(
            post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest))
        ).andExpect(status().isCreated)
            .andReturn().response.contentAsString

        val userId = objectMapper.readTree(userResponse)["id"].asLong()

        val transactionRequest = mapOf(
            "userId" to userId,
            "fromCurrency" to "BRL",
            "fromAmount" to BigDecimal("100.00"),
            "toCurrency" to "USD",
            "accessKey" to "fake-key"
        )

        mockMvc.perform(
            post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionRequest))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.fromCurrency").value("BRL"))
            .andExpect(jsonPath("$.toCurrency").value("USD"))
            .andExpect(jsonPath("$.fromAmount").value("100.0"))

    }

    @Test
    fun `should return all transactions for a user`() {

        val userRequest = mapOf("name" to "Test User", "email" to "test3@example.com")

        val userResponse = mockMvc.perform(
            post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest))
        ).andExpect(status().isCreated)
            .andReturn().response.contentAsString

        val userId = objectMapper.readTree(userResponse)["id"].asLong()

        val transactionRequest = mapOf(
            "userId" to userId,
            "fromCurrency" to "BRL",
            "fromAmount" to BigDecimal("100.00"),
            "toCurrency" to "USD",
            "accessKey" to "fake-key"
        )

        mockMvc.perform(
            post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionRequest))
        ).andExpect(status().isOk)


        mockMvc.perform(
            get("/api/transactions/$userId")
                .contentType(MediaType.APPLICATION_JSON)
        )

            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].fromCurrency").value("BRL"))
            .andExpect(jsonPath("$[0].toCurrency").value("USD"))
    }

}