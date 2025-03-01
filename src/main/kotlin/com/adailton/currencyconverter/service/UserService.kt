package com.adailton.currencyconverter.service

import com.adailton.currencyconverter.dto.UserRequest
import com.adailton.currencyconverter.dto.UserResponse
import com.adailton.currencyconverter.exception.ResourceNotFoundException
import com.adailton.currencyconverter.model.User
import com.adailton.currencyconverter.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    private val logger: Logger = LoggerFactory.getLogger(TransactionService::class.java)
    fun createUser(request: UserRequest): UserResponse {
        logger.info("Creating user with name: ${request.name}, email: ${request.email}")
        val user = User(
            name = request.name,
            email = request.email
        )
        val savedUser = userRepository.save(user)
        return toUserResponse(savedUser)
    }

    fun getUserById(id: Long): UserResponse {
        logger.info("Fetching user with ID: $id")
        val user = userRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("User with ID $id not found.") }
        return toUserResponse(user)
    }

    fun updateUser(id: Long, request: UserRequest): UserResponse {
        logger.info("Updating user with ID: $id, name: ${request.name}, email: ${request.email}")
        val existingUser = userRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("User with ID $id not found.") }

        val updatedUser = existingUser.copy(
            name = request.name,
            email = request.email
        )
        val savedUser = userRepository.save(updatedUser)
        return toUserResponse(savedUser)
    }

    fun deleteUser(id: Long) {
        logger.info("Deleting user with ID: $id")
        if (!userRepository.existsById(id)) {
            throw ResourceNotFoundException("User with ID $id not found.")
        }
        userRepository.deleteById(id)
    }

    fun getAllUsers(): List<UserResponse> {
        logger.info("Fetching all users")
        return userRepository.findAll().map { toUserResponse(it) }
    }

    private fun toUserResponse(user: User): UserResponse {
        return UserResponse(
            id = user.id,
            name = user.name,
            email = user.email
        )
    }
}