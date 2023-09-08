package br.com.giannatech.mercadolivro.controller.request

import br.com.giannatech.mercadolivro.validations.EmailAvailable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest(
	@field:NotEmpty(message = "Name cannot be empty")
	val name: String,

	@field:Email(message = "Email should be valid")
	@EmailAvailable
	val email: String
)