package br.com.giannatech.mercadolivro.events.listeners

import br.com.giannatech.mercadolivro.events.PurchaseEvent
import br.com.giannatech.mercadolivro.service.BookService
import br.com.giannatech.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class UpdateSoldBookStatus(
	private val bookService: BookService
) {
	@Async
	@EventListener
	fun listen(purchaseEvent: PurchaseEvent) {
		bookService.purchase(purchaseEvent.purchase.books)
	}
}