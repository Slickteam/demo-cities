package fr.slickteam.cities.validation

import fr.slickteam.cities.repositories.impl.UserRepository
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Created by jguidoux on 23/05/2017.
 */

class UniqueEmailValidator(val userRepository: UserRepository) : ConstraintValidator<UniqueEmail, String> {


	override fun initialize(constraintAnnotation: UniqueEmail?) {
	}

	override fun isValid(email: String?, context: ConstraintValidatorContext?): Boolean {
		return email != null && !userRepository.containEmail(email);
	}
}