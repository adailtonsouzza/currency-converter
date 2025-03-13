package com.adailton.currencyconverter.response

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal


@Schema(description = "Response payload for a currency conversion transaction")
data class TransactionResponse(
    @field:Schema(description = "ID of the transaction", example = "1")
    val id: Long,

    @field:Schema(description = "ID of the user who performed the transaction", example = "1")
    val userId: Long,

    @field:Schema(description = "Currency code converted from (e.g., BRL)", example = "BRL")
    val fromCurrency: String,

    @field:Schema(description = "Amount in the original currency", example = "100.0")
    val fromAmount: BigDecimal,

    @field:Schema(description = "Currency code converted to (e.g., USD)", example = "USD")
    val toCurrency: String,

    @field:Schema(description = "Converted amount in the target currency", example = "18.50")
    val toAmount: BigDecimal,

    @field:Schema(description = "Conversion rate used", example = "0.185")
    val conversionRate: BigDecimal,

    @field:Schema(description = "Timestamp of the transaction in UTC", example = "2023-10-01T12:34:56Z")
    val timestamp: String
)
