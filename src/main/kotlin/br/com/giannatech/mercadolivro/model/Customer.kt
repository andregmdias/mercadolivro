package br.com.giannatech.mercadolivro.model

import jakarta.persistence.*

@Entity(name = "customer")
@Table(name = "customers")
data class Customer(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Int? = null,

	@Column(name = "name", nullable = false)
	var name: String,

	@Column(name = "email", nullable = false, unique = true)
	var email: String,

	@Column(name ="active", nullable = false)
	var active: Boolean = true
)