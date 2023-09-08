package br.com.giannatech.mercadolivro.service

import br.com.giannatech.mercadolivro.enums.BookStatus
import br.com.giannatech.mercadolivro.enums.Errors
import br.com.giannatech.mercadolivro.exception.InvalidBookStatusException
import br.com.giannatech.mercadolivro.exception.NotFoundException
import br.com.giannatech.mercadolivro.model.Book
import br.com.giannatech.mercadolivro.model.Customer
import br.com.giannatech.mercadolivro.model.Purchase
import br.com.giannatech.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
	val bookRepository: BookRepository
) {

	fun findAll(pageable: Pageable): Page<Book> =
		bookRepository.findAll(pageable)


	fun findActive(pageable: Pageable): Page<Book> =
		bookRepository.findByStatus(BookStatus.ATIVO, pageable)


	fun create(book: Book) {
		bookRepository.save(book)
	}

	fun getBookById(id: Int): Book {
		return bookRepository
			.findById(id)
			.orElseThrow{ NotFoundException(Errors.ML101.message.format(id), Errors.ML101.code) }
	}

	fun update(book: Book) {
		bookRepository.save(book)
	}

	fun deleteById(id: Int) {
		val book = getBookById(id)
		book.status = BookStatus.CANCELADO
		update(book)
	}

	fun deleteByCustomer(customer: Customer) {
		val books = bookRepository.findByCustomer(customer)
		books.forEach { book ->
			book.status = BookStatus.DELETADO
		}
		bookRepository.saveAll(books)
	}

	fun findAllById(bookIds: Set<Int>): List<Book> =
		bookRepository.findAllById(bookIds).toList()

	fun purchase(books: MutableList<Book>) {
		books.map {
			it.status = BookStatus.VENDIDO
		}
		bookRepository.saveAll(books)
	}

	fun checkValidBookStatusToPurchase(book: Book) {
		if (book.status != BookStatus.ATIVO) {
			throw InvalidBookStatusException(Errors.ML103.message, Errors.ML103.code)
		}
	}
}