package com.adailton.currencyconverter.repository


import com.adailton.currencyconverter.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findByUserIdOrderByTimestampDesc(userId: Long): List<Transaction>

}