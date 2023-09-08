package br.com.giannatech.mercadolivro.exception

class BadRequestException(
	override val message: String,
	val errorCode: String
): Exception()