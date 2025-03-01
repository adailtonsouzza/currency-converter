package com.adailton.currencyconverter.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class ExchangeRateService (
    @Value("\${exchange.api.url}") private val apiUrl: String
){

    private val logger: Logger = LoggerFactory.getLogger(TransactionService::class.java)

    private val webClient: WebClient = WebClient.builder()
        .baseUrl(apiUrl)
        .build()

    fun getExchangeRate(fromCurrency: String, toCurrency: String, accessKey: String): BigDecimal {
        logger.info("Fetching exchange rate fromCurrency: $fromCurrency, toCurrency: $toCurrency, accessKey: $accessKey")
        val urlWithParams = "$apiUrl?access_key=$accessKey&format=1"

        val exchangeData: ExchangeRateResponse = webClient.get()
            .uri(urlWithParams)
            .retrieve()
            .bodyToMono(ExchangeRateResponse::class.java)
            .block() ?: throw RuntimeException("Failed to fetch exchange rates")

        val fromRate = exchangeData.rates[fromCurrency]
            ?.let { BigDecimal.valueOf(it) }
            ?: throw IllegalArgumentException("Invalid fromCurrency: $fromCurrency")

        val toRate = exchangeData.rates[toCurrency]
            ?.let { BigDecimal.valueOf(it) }
            ?: throw IllegalArgumentException("Invalid toCurrency: $toCurrency")

        return toRate.divide(fromRate, 6,  RoundingMode.HALF_UP)
    }
}

data class ExchangeRateResponse(
    val rates: Map<String, Double>
)
