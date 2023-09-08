package br.com.giannatech.mercadolivro.repository

import br.com.giannatech.mercadolivro.model.Purchase
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository : CrudRepository<Purchase, Int>