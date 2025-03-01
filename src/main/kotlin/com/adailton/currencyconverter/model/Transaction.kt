package com.adailton.currencyconverter.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(name = "transactions")
data class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "from_currency", nullable = false, length = 3)
    val fromCurrency: String,

    @Column(name = "from_amount", nullable = false)
    val fromAmount: BigDecimal,

    @Column(name = "to_currency", nullable = false, length = 3)
    val toCurrency: String,

    @Column(name = "to_amount", nullable = false)
    val toAmount: BigDecimal,

    @Column(name = "conversion_rate", nullable = false)
    val conversionRate: BigDecimal,

    @Column(name = "timestamp", nullable = false)
    @CreationTimestamp
    val timestamp: LocalDateTime = LocalDateTime.now()
)
