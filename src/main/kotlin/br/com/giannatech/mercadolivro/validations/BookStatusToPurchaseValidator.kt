package br.com.giannatech.mercadolivro.validations

import br.com.giannatech.mercadolivro.enums.BookStatus
import br.com.giannatech.mercadolivro.exception.InvalidBookStatusException
import br.com.giannatech.mercadolivro.model.Book
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

class BookStatusToPurchaseValidator:Validator {
	override fun supports(clazz: Class<*>): Boolean =
		Book::class.java.isAssignableFrom(clazz)

	override fun validate(target: Any, errors: Errors) {
		val book = target as Book
		if (book.status != BookStatus.ATIVO) {
			errors.rejectValue("status", "400", "Book status is not ATIVO")
		}
	}

}