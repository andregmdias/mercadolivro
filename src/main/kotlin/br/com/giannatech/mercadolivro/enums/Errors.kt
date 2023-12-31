package br.com.giannatech.mercadolivro.enums

enum class Errors(val code: String, val message: String) {
	ML001("ML-001", "Invalid request"),
	ML101("ML-101", "Book [%s] not found"),
	ML102("ML-102", "Cannot update a book with status [%s]"),
	ML103("ML-103", "Book [%s] is not available"),
	ML201("ML-201", "Customer [%s] not found"),
}