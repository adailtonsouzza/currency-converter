package com.adailton.currencyconverter.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Response payload for user details")
data class UserResponse(

    @Schema(description = "User's ID", example = "1")
    val id: Long,

    @Schema(description = "User's name", example = "John Doe")
    val name: String,

    @Schema(description = "User's email", example = "john@example.com")
    val email: String
)
