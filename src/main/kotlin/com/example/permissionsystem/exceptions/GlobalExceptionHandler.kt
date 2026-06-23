package com.example.permissionsystem.exceptions

import com.example.permissionsystem.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AppExceptions.UserNotFoundException::class)
    fun handleUserNotFound(ex: AppExceptions.UserNotFoundException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            errorCode = "USER_NOT_FOUND",
            message = ex.message,
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(AppExceptions.RoleNotFoundException::class)
    fun handleRoleNotFound(ex: AppExceptions.RoleNotFoundException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            errorCode = "ROLE_NOT_FOUND",
            message = ex.message,
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleExceptions(ex: Exception): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            errorCode = "INTERNAL_SERVER_ERROR",
            message = ex.message,
        )
        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}