package com.example.victor.facesmaps.util

class ErrorHandler: Errors {
    override fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
    }
}