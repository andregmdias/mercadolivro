package br.com.giannatech.mercadolivro.controller

import br.com.giannatech.mercadolivro.controller.request.PostCustomerRequest
import br.com.giannatech.mercadolivro.controller.request.PutCustomerRequest
import br.com.giannatech.mercadolivro.controller.response.CustomerResponse
import br.com.giannatech.mercadolivro.controller.response.ErrorResponse
import br.com.giannatech.mercadolivro.extension.toCustomerModel
import br.com.giannatech.mercadolivro.extension.toResponse
import br.com.giannatech.mercadolivro.service.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(
	val customerService: CustomerService
) {

	@GetMapping
	fun index(@RequestParam name: String?): List<CustomerResponse> {
		return customerService.getAll(name).map { it.toResponse() }
	}

	@Operation(summary = "Show Customer", description = "This endpoint is responsible to get a customer by id")
	@ApiResponses(
		value = [
			ApiResponse(
				responseCode = "200", description = "Success",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = CustomerResponse::class))]
			),
			ApiResponse(responseCode = "404", description = "Not Found",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))])
		]
	)
	@GetMapping("/{id}")
	fun show(@PathVariable id: Int): CustomerResponse? {
		return customerService.getCustomerById(id)!!.toResponse()
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun create(@RequestBody @Valid customer: PostCustomerRequest) {
		customerService.create(customer.toCustomerModel())
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun update(@PathVariable id: Int, @RequestBody @Valid customer: PutCustomerRequest) {
		val customerFound = customerService.getCustomerById(id)
		customerService.update(customer.toCustomerModel(customerFound!!))
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun delete(@PathVariable id: Int) {
		customerService.deleteById(id)
	}
}