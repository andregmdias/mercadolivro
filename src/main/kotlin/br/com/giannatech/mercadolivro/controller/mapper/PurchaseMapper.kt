package br.com.giannatech.mercadolivro.controller.mapper

import br.com.giannatech.mercadolivro.controller.request.PostPurchaseRequest
import br.com.giannatech.mercadolivro.model.Purchase
import br.com.giannatech.mercadolivro.service.BookService
import br.com.giannatech.mercadolivro.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
	private val bookService: BookService,
	private val cutomerService: CustomerService
) {

	fun toModel(request: PostPurchaseRequest): Purchase {
		val customer = cutomerService.getCustomerById(request.customerId)
		val books = bookService.findAllById(request.bookIds)

		return Purchase(
			customer = customer!!,
			books = books.toMutableList(),
			price = books.sumOf { it.price }
		)
	}

}