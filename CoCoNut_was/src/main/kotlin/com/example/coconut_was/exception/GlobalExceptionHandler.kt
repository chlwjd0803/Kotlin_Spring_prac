package com.example.coconut_was.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.MultipartException
import org.springframework.web.multipart.support.MissingServletRequestPartException


@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    // 커스텀 예외처리
    @ExceptionHandler(CustomException::class)
    protected fun customExceptionHandler(e: CustomException): ResponseEntity<ErrorDto> {
        val errorCode = e.errorCode
        val errorDto = ErrorDto(errorCode.status, errorCode.message)
        return ResponseEntity(errorDto, HttpStatus.valueOf(errorCode.status))
    }

    // 일반 예외처리
    @ExceptionHandler(Exception::class)
    protected fun customServerException(e: Exception): ResponseEntity<ErrorDto> {
        log.error("INTERNAL_SERVER_ERROR", e)
        val errorDto = ErrorDto(ErrorCode.INTERNAL_SERVER_ERROR.status, ErrorCode.INTERNAL_SERVER_ERROR.message)
        return ResponseEntity(errorDto, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    // 메소드 인자 타당성 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleValidationException(e: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errors = e.bindingResult.fieldErrors.associate { it.field to (it.defaultMessage ?: "") }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    // RequestPart에 대한 누락
    @ExceptionHandler(MissingServletRequestPartException::class)
    protected fun handleMissingServletRequestPartException(e: MissingServletRequestPartException): ResponseEntity<ErrorDto> {
        log.error("MissingServletRequestPartException", e)
        val errorDto = ErrorDto(ErrorCode.REQUIRED_SUBMISSION_INFO.status, ErrorCode.REQUIRED_SUBMISSION_INFO.message)
        return ResponseEntity(errorDto, HttpStatus.BAD_REQUEST)
    }


    // Multipart 요청의 형식이 잘못되었을 때 발생하는 예외
    @ExceptionHandler(MultipartException::class)
    protected fun handleMultipartException(e: MultipartException): ResponseEntity<ErrorDto> {
        log.error("MultipartException", e)
        val errorDto = ErrorDto(ErrorCode.REQUIRED_SUBMISSION_INFO.status, ErrorCode.REQUIRED_SUBMISSION_INFO.message)
        return ResponseEntity(errorDto, HttpStatus.BAD_REQUEST)
    }
}