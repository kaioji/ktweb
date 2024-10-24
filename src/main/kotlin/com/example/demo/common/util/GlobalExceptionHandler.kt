package com.example.demo.common.util

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleException(error:MethodArgumentNotValidException):ResponseEntity<ValidationErrorResponse>{
        val errors = error.bindingResult.fieldErrors.map { it.defaultMessage?: "Unknown error occurred!" }
        val response = ValidationErrorResponse("Validation failed",4000,errors)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}