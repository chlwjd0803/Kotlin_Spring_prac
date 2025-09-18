package com.example.coconut_was.exception

class CustomException(val errorCode: ErrorCode) : RuntimeException() {}