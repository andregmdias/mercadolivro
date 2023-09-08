package br.com.giannatech.mercadolivro.service

import br.com.giannatech.mercadolivro.events.PurchaseEvent
import br.com.giannatech.mercadolivro.model.Purchase
import br.com.giannatech.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
	private val purchaseRepository: PurchaseRepository,
	private val bookService: BookService,
	private val applicationEventPublisher: ApplicationEventPublisher
) {

	fun create(purchase: Purchase) {
		purchaseRepository.save(purchase)
		purchase.books.forEach { book ->
			bookService.checkValidBookStatusToPurchase(book)
		}
		applicationEventPublisher.publishEvent(PurchaseEvent(this, purchase))
	}

	fun update(purchase: Purchase) {
		purchaseRepository.save(purchase)
	}
}
