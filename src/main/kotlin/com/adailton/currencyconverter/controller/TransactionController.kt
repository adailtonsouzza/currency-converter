package com.adailton.currencyconverter.controller

import com.adailton.currencyconverter.dto.TransactionRequest
import com.adailton.currencyconverter.dto.TransactionResponse
import com.adailton.currencyconverter.service.TransactionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transactions", description = "Endpoints for currency transactions")
class TransactionController(private val transactionService: TransactionService) {

    @PostMapping
    @Operation(
        summary = "Perform a currency conversion",
        description = "Converts an amount from one currency to another and saves the transaction.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Transaction created successfully",
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid request (e.g., missing fields, invalid currency, or negative amount)"
            ),
            ApiResponse(
                responseCode = "404",
                description = "User not found"
            ),
            ApiResponse(
                responseCode = "502",
                description = "Exchange rate service is unavailable"
            )
        ]
    )
    fun createTransaction(@Valid @RequestBody request: TransactionRequest): ResponseEntity<TransactionResponse> {
        val transactionResponse = transactionService.createTransaction(request)
        return ResponseEntity.ok(transactionResponse)
    }

    @GetMapping("/{userId}")
    @Operation(
        summary = "Get all transactions for a user",
        description = "Retrieves all transactions made by a specific user.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "List of transactions returned successfully",
            ),
            ApiResponse(
                responseCode = "404",
                description = "User not found"
            )
        ]
    )
    fun getTransactionsByUser(@PathVariable userId: Long): ResponseEntity<List<TransactionResponse>> {
        val transactions = transactionService.getTransactionsByUser(userId)
        return ResponseEntity.ok(transactions)
    }
}