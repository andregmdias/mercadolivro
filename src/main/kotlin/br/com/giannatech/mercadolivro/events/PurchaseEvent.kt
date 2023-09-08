package br.com.giannatech.mercadolivro.events

import br.com.giannatech.mercadolivro.model.Purchase
import org.springframework.context.ApplicationEvent

class PurchaseEvent(
	source: Any,
	val purchase: Purchase
): ApplicationEvent(source)