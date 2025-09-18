package com.example.coconut_was.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil {

    @Value("\${jwt.secret-key}")
    private lateinit var secretKey: String

    companion object {
        private const val ACCESS_VALID_TIME = 1000L * 60 * 60 // 1시간
        private const val REFRESH_VALID_TIME = 1000L * 60 * 60 * 24 * 7 // 7일
    }

    // 1. Access 토큰 발급
    fun createAccessToken(id: Long, email: String, role: String): String {
        return generateToken(id, email, ACCESS_VALID_TIME, role)
    }

    // 2. Refresh 토큰 발급
    fun createRefreshToken(id: Long, email: String, role: String): String {
        return generateToken(id, email, REFRESH_VALID_TIME, role)
    }

    private fun generateToken(id: Long, email: String, validTime: Long, role: String): String {
        val now = System.currentTimeMillis()
        return Jwts.builder()
            .subject(id.toString())
            .claim("email", email)
            .claim("role", role)
            .issuedAt(Date(now))
            .expiration(Date(now + validTime))
            .signWith(getSigningKey())
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
            true
        } catch (e: JwtException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return bearerToken?.takeIf { it.startsWith("Bearer ") }?.substring(7)
    }

    private fun getSigningKey(): SecretKey {
        val keyBytes = secretKey.toByteArray(StandardCharsets.UTF_8)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    private fun getClaims(token: String): Jws<Claims> {
        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
    }

    fun getEmailFromToken(token: String): String {
        return getClaims(token).payload.get("email", String::class.java)
    }

    fun getIdFromToken(token: String): Long {
        return getClaims(token).payload.subject.toLong()
    }
}