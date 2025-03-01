package com.adailton.currencyconverter.repository

import com.adailton.currencyconverter.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}