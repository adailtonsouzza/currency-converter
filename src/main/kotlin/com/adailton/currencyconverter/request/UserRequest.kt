package com.adailton.currencyconverter.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Schema(description = "Request payload for creating or updating a user")
data class UserRequest(

    @Schema(description = "User's name", example = "John Doe")
    @field:NotBlank(message = "Name is required")
    @field:Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    val name: String,

    @Schema(description = "User's email", example = "john@example.com")
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email must be valid")
    val email: String
)
