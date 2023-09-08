package br.com.giannatech.mercadolivro.exception

import br.com.giannatech.mercadolivro.controller.response.ErrorResponse
import br.com.giannatech.mercadolivro.controller.response.FieldErrorResponse
import br.com.giannatech.mercadolivro.enums.Errors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

	@ExceptionHandler(NotFoundException::class)
	fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse>{
		val errorResponse = ErrorResponse(
			HttpStatus.NOT_FOUND.value(),
			ex.message,
			ex.errorCode,
			null
		)

		return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
	}

	@ExceptionHandler(BadRequestException::class)
	fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse>{
		val errorResponse = ErrorResponse(
			HttpStatus.BAD_REQUEST.value(),
			ex.message,
			ex.errorCode,
			null
		)

		return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
	}

	@ExceptionHandler(MethodArgumentNotValidException::class)
	fun handleMethodArgumentNotValidExceptionException(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse>{
		val errorResponse = ErrorResponse(
			HttpStatus.UNPROCESSABLE_ENTITY.value(),
			Errors.ML001.message,
			Errors.ML001.code,
			ex.bindingResult.fieldErrors.map { FieldErrorResponse(it.defaultMessage ?: "invalid", it.field) }
		)

		return ResponseEntity(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY)
	}

	@ExceptionHandler(InvalidBookStatusException::class)
	fun handleInvalidBookStatusException(ex: InvalidBookStatusException, request: WebRequest): ResponseEntity<ErrorResponse>{
		val errorResponse = ErrorResponse(
			HttpStatus.UNPROCESSABLE_ENTITY.value(),
			ex.message,
			ex.errorCode,
			null
		)

		return ResponseEntity(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY)
	}
}