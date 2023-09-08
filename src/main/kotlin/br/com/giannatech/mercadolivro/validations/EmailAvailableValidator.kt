package br.com.giannatech.mercadolivro.validations

import br.com.giannatech.mercadolivro.service.CustomerService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EmailAvailableValidator(private var customerService: CustomerService): ConstraintValidator<EmailAvailable, String> {
	override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
		if(value.isNullOrEmpty()) {
			return false
		}

		return customerService.existsByEmail(value).not()
	}

}
