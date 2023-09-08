package br.com.giannatech.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class PostBookRequest(

	@field:NotEmpty(message = "Name cannot be empty")
	val name: String,

	@field:NotNull(message = "Price cannot be empty")
	val price: BigDecimal,

	@JsonAlias("customer_id")
	val customerId: Int
)