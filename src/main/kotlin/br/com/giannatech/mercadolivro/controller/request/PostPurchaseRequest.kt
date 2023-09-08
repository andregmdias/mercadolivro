package br.com.giannatech.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class PostPurchaseRequest(

	@field:NotNull(message = "Customer id cannot be empty")
	@field:Positive(message = "Customer should be greater than zero")
	@JsonAlias("customer_id")
	val customerId: Int,

	@field:NotNull
	@JsonAlias("book_ids")
	val bookIds: Set<Int>
)

