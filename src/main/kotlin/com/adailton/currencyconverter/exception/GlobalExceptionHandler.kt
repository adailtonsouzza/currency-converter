package com.adailton.currencyconverter.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(ex: ResourceNotFoundException): ResponseEntity<ErrorResponse> {
        logger.error("Resource not found: ${ex.message}")
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = ex.bindingResult.fieldErrors.map {
            FieldError(it.field, it.defaultMessage ?: "Validation error")
        }
        logger.warn("Validation error: ${errors.joinToString { "${it.field} - ${it.message}" }}")
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = "Invalid data",
            errors = errors
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "An unexpected error occurred."
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(InvalidCurrencyException::class)
    fun handleInvalidCurrencyException(ex: InvalidCurrencyException): ResponseEntity<ErrorResponse> {
        logger.error("Unexpected error occurred: ${ex.message}", ex)
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = ex.message
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ExternalApiException::class)
    fun handleExternalApiException(ex: ExternalApiException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_GATEWAY.value(),
            message = "Exchange rate service is unavailable. Try again later."
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_GATEWAY)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = ex.message
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException): ResponseEntity<ErrorResponse> {
        val errorMessage = if (ex.message?.contains("USERS(EMAIL") == true) {
            "Email already exists."
        } else {
            "Data integrity violation."
        }

        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = errorMessage
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

}

data class ErrorResponse(
    val status: Int,
    val message: String?,
    val errors: List<FieldError>? = null
)

data class FieldError(
    val field: String,
    val message: String?
)
