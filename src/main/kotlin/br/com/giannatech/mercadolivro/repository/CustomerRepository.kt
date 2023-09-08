package br.com.giannatech.mercadolivro.repository

import br.com.giannatech.mercadolivro.model.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : CrudRepository<Customer, Int> {
	fun findByNameContainingIgnoreCase(name: String): List<Customer>
	fun existsByEmail(value: String): Boolean
}