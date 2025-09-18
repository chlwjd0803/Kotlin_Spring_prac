package com.example.coconut_was.exception

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

@Component
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"

        val errorDto = ErrorDto(HttpStatus.UNAUTHORIZED.value(), "토큰이 없거나 만료되었습니다.")

        val objectMapper = ObjectMapper()
        response.writer.write(objectMapper.writeValueAsString(errorDto))
    }
}