package br.com.giannatech.mercadolivro.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(name = "purchase")
@Table(name = "purchases")
data class Purchase(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Int? = null,

	@ManyToOne
	@JoinColumn(name = "customer_id")
	var customer: Customer,

	@ManyToMany
	@JoinTable(
		name = "purchases_books",
		joinColumns = [JoinColumn(name = "purchase_id")],
		inverseJoinColumns = [JoinColumn(name = "book_id")]
	)
	val books: MutableList<Book>,

	@Column(name = "nfe", nullable = true)
	val nfe: String? = null,

	@Column(name = "price")
	val price: BigDecimal,

	@Column(name = "created_at")
	val createdAt: LocalDateTime = LocalDateTime.now()
)