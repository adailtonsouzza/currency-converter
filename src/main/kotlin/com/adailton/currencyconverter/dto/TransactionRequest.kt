package com.adailton.currencyconverter.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.math.BigDecimal

@Schema(description = "Request payload for currency conversion")
data class TransactionRequest(
    @field:NotNull(message = "User ID is required")
    @field:Schema(description = "ID of the user performing the transaction", example = "1")
    val userId: Long,

    @field:NotBlank(message = "From currency is required")
    @field:Size(min = 3, max = 3, message = "From currency must be 3 characters")
    @field:Schema(description = "Currency code to convert from (e.g., BRL)", example = "BRL")
    val fromCurrency: String,

    @field:NotNull(message = "From amount is required")
    @field:Positive(message = "From amount must be positive")
    @field:Schema(description = "Amount to convert", example = "100.0")
    val fromAmount: BigDecimal,

    @field:NotBlank(message = "To currency is required")
    @field:Size(min = 3, max = 3, message = "To currency must be 3 characters")
    @field:Schema(description = "Currency code to convert to (e.g., USD)", example = "USD")
    val toCurrency: String,

    @field:NotBlank(message = "API access key is required")
    @Schema(description = "User's access key for the currency API", example = "your_api_key_here")
    val accessKey: String
)
