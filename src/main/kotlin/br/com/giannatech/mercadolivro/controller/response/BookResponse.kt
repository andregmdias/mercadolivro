package br.com.giannatech.mercadolivro.controller.response

import br.com.giannatech.mercadolivro.enums.BookStatus
import java.math.BigDecimal

data class BookResponse(
	var id: Int? = null,
	var name: String,
	var price: BigDecimal,
	var customer: CustomerResponse? = null,
	var status: BookStatus?
)
