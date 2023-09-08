package br.com.giannatech.mercadolivro.service

import br.com.giannatech.mercadolivro.enums.Errors
import br.com.giannatech.mercadolivro.exception.NotFoundException
import br.com.giannatech.mercadolivro.model.Customer
import br.com.giannatech.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
	val customerRepository: CustomerRepository,
	val bookService: BookService
) {

	fun getAll(name: String?): List<Customer> {
		name?.let { it ->
			return customerRepository.findByNameContainingIgnoreCase(it)
		}
		return customerRepository.findAll().toList()
	}

	fun getCustomerById(id: Int): Customer? {
		return customerRepository
			.findById(id)
			.orElseThrow{ NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code) }
	}

	fun create(customer: Customer) {
		customerRepository.save(customer)
	}

	fun update(customer: Customer) {
		if (!customerRepository.existsById(customer.id!!)) {
			throw Exception()
		}
		customerRepository.save(customer)
	}

	fun deleteById(id: Int) {
		val customer = getCustomerById(id)
		bookService.deleteByCustomer(customer!!)
		customer.active = false
		customerRepository.save(customer)
	}

	fun existsByEmail(value: String): Boolean =
		customerRepository.existsByEmail(value)
}