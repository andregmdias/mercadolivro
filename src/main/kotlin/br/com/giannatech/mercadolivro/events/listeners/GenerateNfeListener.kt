package br.com.giannatech.mercadolivro.events.listeners

import br.com.giannatech.mercadolivro.events.PurchaseEvent
import br.com.giannatech.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateNfeListener(
	private val purchaseService: PurchaseService
) {
	@Async
	@EventListener
	fun listen(purchaseEvent: PurchaseEvent) {
		val nfe = UUID.randomUUID().toString()
		val purchase = purchaseEvent.purchase.copy(nfe = nfe)

		purchaseService.update(purchase)
	}
}