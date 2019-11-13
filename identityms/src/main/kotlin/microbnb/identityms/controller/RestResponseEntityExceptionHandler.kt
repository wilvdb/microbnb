package microbnb.identityms.controller

import microbnb.identityms.exception.NotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity("No resource available", HttpHeaders(), HttpStatus.NOT_FOUND)
    }
}