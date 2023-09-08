package br.com.giannatech.mercadolivro.repository

import br.com.giannatech.mercadolivro.enums.BookStatus
import br.com.giannatech.mercadolivro.model.Book
import br.com.giannatech.mercadolivro.model.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Int> {
	fun findByCustomer(customer: Customer): List<Book>
	fun findByStatus(status: BookStatus, pageable: Pageable): Page<Book>
}