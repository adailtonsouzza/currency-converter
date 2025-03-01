package com.adailton.currencyconverter.service

import com.adailton.currencyconverter.dto.TransactionRequest
import com.adailton.currencyconverter.dto.TransactionResponse
import com.adailton.currencyconverter.exception.ResourceNotFoundException
import com.adailton.currencyconverter.model.Transaction
import com.adailton.currencyconverter.repository.TransactionRepository
import com.adailton.currencyconverter.repository.UserRepository
import org.springframework.stereotype.Service
import java.math.RoundingMode
import java.time.LocalDateTime

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val exchangeRateService: ExchangeRateService,
    private val userRepository: UserRepository
) {

    fun createTransaction(request: TransactionRequest): TransactionResponse {
        val user = userRepository.findById(request.userId)
            .orElseThrow { ResourceNotFoundException("User with ID ${request.userId} not found.") }

        val rate = exchangeRateService.getExchangeRate(request.fromCurrency, request.toCurrency, request.accessKey)

        val toAmount = request.fromAmount.multiply(rate).setScale(2, RoundingMode.HALF_UP)

        val transaction = Transaction(
            userId = user.id,
            fromCurrency = request.fromCurrency,
            fromAmount = request.fromAmount,
            toCurrency = request.toCurrency,
            toAmount = toAmount,
            conversionRate = rate,
            timestamp = LocalDateTime.now()
        )

        val savedTransaction = transactionRepository.save(transaction)

        return toTransactionResponse(savedTransaction)
    }

    fun getTransactionsByUser(userId: Long): List<TransactionResponse> {
        return transactionRepository.findByUserIdOrderByTimestampDesc(userId).map { toTransactionResponse(it) }
    }

    private fun toTransactionResponse(transaction: Transaction): TransactionResponse {
        return TransactionResponse(
            id = transaction.id,
            userId = transaction.userId,
            fromCurrency = transaction.fromCurrency,
            fromAmount = transaction.fromAmount,
            toCurrency = transaction.toCurrency,
            toAmount = transaction.toAmount,
            conversionRate = transaction.conversionRate,
            timestamp = transaction.timestamp.toString()
        )
    }
}