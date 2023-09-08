package br.com.giannatech.mercadolivro.controller.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PutCustomerRequest(
	@field:NotEmpty(message = "Name cannot be empty")
	val name: String,
	@field:Email(message = "Email should be valid")
	val email: String
)