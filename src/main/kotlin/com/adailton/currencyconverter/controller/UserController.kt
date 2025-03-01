package com.adailton.currencyconverter.controller

import com.adailton.currencyconverter.dto.UserRequest
import com.adailton.currencyconverter.dto.UserResponse
import com.adailton.currencyconverter.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "Endpoints for user management")
class UserController(private val userService: UserService) {

    @PostMapping
    @Operation(
        summary = "Create a new user",
        responses = [
            ApiResponse(responseCode = "201", description = "User created successfully"),
            ApiResponse(responseCode = "400", description = "Invalid data")
        ]
    )
    fun createUser(@Valid @RequestBody request: UserRequest): ResponseEntity<UserResponse> {
        val userResponse = userService.createUser(request)
        return ResponseEntity(userResponse, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get a user by ID",
        responses = [
            ApiResponse(responseCode = "200", description = "User found"),
            ApiResponse(responseCode = "404", description = "User not found")
        ]
    )
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserResponse> {
        val userResponse = userService.getUserById(id)
        return ResponseEntity(userResponse, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update an existing user",
        responses = [
            ApiResponse(responseCode = "200", description = "User updated successfully"),
            ApiResponse(responseCode = "404", description = "User not found")
        ]
    )
    fun updateUser(@PathVariable id: Long, @Valid @RequestBody request: UserRequest): ResponseEntity<UserResponse> {
        val userResponse = userService.updateUser(id, request)
        return ResponseEntity(userResponse, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a user by ID",
        responses = [
            ApiResponse(responseCode = "204", description = "User deleted successfully"),
            ApiResponse(responseCode = "404", description = "User not found")
        ]
    )
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        userService.deleteUser(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping
    @Operation(
        summary = "List all users",
        responses = [
            ApiResponse(responseCode = "200", description = "List of users returned successfully")
        ]
    )
    fun getAllUsers(): ResponseEntity<List<UserResponse>> {
        val users = userService.getAllUsers()
        return ResponseEntity(users, HttpStatus.OK)
    }
}