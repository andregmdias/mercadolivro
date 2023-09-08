package br.com.giannatech.mercadolivro.model

import br.com.giannatech.mercadolivro.enums.BookStatus
import br.com.giannatech.mercadolivro.enums.Errors
import br.com.giannatech.mercadolivro.exception.BadRequestException
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "book")
@Table(name = "books")
class Book(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Int? = null,

	@Column(name = "name", nullable = false)
	var name: String,

	@Column(name = "price", nullable = false)
	var price: BigDecimal,

	@ManyToOne
	@JoinColumn(name = "customer_id")
	var customer: Customer
){
	@Column
	@Enumerated(EnumType.STRING)
	var status: BookStatus? = null
		set(value) {
			if(field == BookStatus.CANCELADO || field == BookStatus.DELETADO) {
				throw BadRequestException(Errors.ML102.message.format(field), Errors.ML102.code)
			}
			field = value
		}

	constructor(
		id: Int? = null,
		name: String,
		price: BigDecimal,
		customer: Customer,
		status: BookStatus?
	) : this(id, name, price, customer) {
		this.status = status
	}

}