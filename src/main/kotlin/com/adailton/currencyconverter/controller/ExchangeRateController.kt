package com.adailton.currencyconverter.controller

import com.adailton.currencyconverter.service.ExchangeRateService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/api/exchange")
class ExchangeRateController(private val exchangeRateService: ExchangeRateService) {

    @GetMapping("/{from}/{to}")
    fun getRate(@PathVariable from: String, @PathVariable to: String): ResponseEntity<BigDecimal> {
        val rate = exchangeRateService.getExchangeRate(from, to)
        return ResponseEntity.ok(rate)
    }
}