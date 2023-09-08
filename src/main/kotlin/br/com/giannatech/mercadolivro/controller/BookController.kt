package br.com.giannatech.mercadolivro.controller

import br.com.giannatech.mercadolivro.controller.request.PostBookRequest
import br.com.giannatech.mercadolivro.controller.request.PutBookRequest
import br.com.giannatech.mercadolivro.controller.response.BookResponse
import br.com.giannatech.mercadolivro.controller.response.ErrorResponse
import br.com.giannatech.mercadolivro.extension.toBookModel
import br.com.giannatech.mercadolivro.extension.toResponse
import br.com.giannatech.mercadolivro.service.BookService
import br.com.giannatech.mercadolivro.service.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(
	val bookService: BookService,
	val customerService: CustomerService
) {

	@GetMapping
	fun index(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
		return bookService.findAll(pageable).map { it.toResponse() }
	}

	@GetMapping("/active")
	fun findActive(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
		return bookService.findActive(pageable).map { it.toResponse() }
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun create(@RequestBody @Valid book: PostBookRequest) {
		val customer = customerService.getCustomerById(book.customerId)
		bookService.create(book.toBookModel(customer!!))
	}

	@Operation(summary = "Show Customer", description = "This endpoint is responsible to get a customer by id")
	@ApiResponses(
		value = [
			ApiResponse(
				responseCode = "200", description = "Success",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = BookResponse::class))]
			),
			ApiResponse(responseCode = "404", description = "Not Found",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))])
		]
	)
	@GetMapping("/{id}")
	fun show(@PathVariable id: Int): BookResponse {
		return bookService.getBookById(id).toResponse()
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest) {
		val fetched = bookService.getBookById(id)
		bookService.update(book.toBookModel(fetched))
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun delete(@PathVariable id: Int) {
		bookService.deleteById(id)
	}
}