package br.com.giannatech.mercadolivro.exception

class InvalidBookStatusException(
	override val message: String,
	val errorCode: String
): Exception()
