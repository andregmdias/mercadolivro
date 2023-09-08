package br.com.giannatech.mercadolivro.extension

import br.com.giannatech.mercadolivro.controller.response.BookResponse
import br.com.giannatech.mercadolivro.controller.request.PostBookRequest
import br.com.giannatech.mercadolivro.controller.request.PostCustomerRequest
import br.com.giannatech.mercadolivro.controller.request.PutBookRequest
import br.com.giannatech.mercadolivro.controller.request.PutCustomerRequest
import br.com.giannatech.mercadolivro.controller.response.CustomerResponse
import br.com.giannatech.mercadolivro.enums.BookStatus
import br.com.giannatech.mercadolivro.model.Book
import br.com.giannatech.mercadolivro.model.Customer

fun PostCustomerRequest.toCustomerModel(): Customer {
	return Customer(
		name = this.name,
		email = this.email,
		active = true
	)
}

fun PutCustomerRequest.toCustomerModel(customer: Customer): Customer {
	return Customer(id = customer.id, this.name, this.email, active = customer.active)
}

fun PostBookRequest.toBookModel(customer: Customer): Book {
	return Book(
		name = this.name,
		price = this.price,
		customer = customer,
		status = BookStatus.ATIVO
	)
}

fun PutBookRequest.toBookModel(book: Book): Book {
	return Book(
		id = book.id,
		name = this.name ?: book.name,
		price = this.price ?: book.price,
		status = book.status,
		customer = book.customer
	)
}


fun Customer.toResponse(): CustomerResponse {
	return CustomerResponse(
		id = this.id!!,
		name = this.name,
		email = this.email
	)
}

fun Book.toResponse(): BookResponse {
	return BookResponse(
		id = this.id,
		name = this.name,
		price = this.price,
		customer = this.customer.toResponse(),
		status = this.status
	)
}
