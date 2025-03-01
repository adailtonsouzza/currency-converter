package com.adailton.currencyconverter.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class ExchangeRateService {
    private val apiUrl = "http://api.exchangeratesapi.io/latest?access_key=4e6796bab9e47f8b6ef69a1231293eee&format=1?base=EUR"

    private val webClient: WebClient = WebClient.builder()
        .baseUrl(apiUrl)
        .build()

    fun getExchangeRate(fromCurrency: String, toCurrency: String): BigDecimal {
        val exchangeData: ExchangeRateResponse = webClient.get()
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
