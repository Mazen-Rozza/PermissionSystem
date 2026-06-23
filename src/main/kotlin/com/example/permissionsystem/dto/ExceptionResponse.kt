package com.example.permissionsystem.dto

data class ErrorResponse(
    val status: Int,
    val errorCode: String,
    val message: String?,
)