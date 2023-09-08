package br.com.giannatech.mercadolivro.controller

import br.com.giannatech.mercadolivro.controller.mapper.PurchaseMapper
import br.com.giannatech.mercadolivro.controller.request.PostPurchaseRequest
import br.com.giannatech.mercadolivro.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/purchases")
class PurchaseController(
	private val purchaseService: PurchaseService,
	private val puchaseMapper: PurchaseMapper
) {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun create(@RequestBody request: PostPurchaseRequest) {
		purchaseService.create(puchaseMapper.toModel(request))
	}
}