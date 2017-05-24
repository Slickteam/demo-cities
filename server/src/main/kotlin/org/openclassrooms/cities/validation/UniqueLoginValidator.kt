package org.openclassrooms.cities.validation

import org.openclassrooms.cities.repositories.impl.UserRepository
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Created by jguidoux on 23/05/2017.
 */

class UniqueLoginValidator(val userRepository: UserRepository) : ConstraintValidator<UniqueLogin, String> {


	override fun initialize(constraintAnnotation: UniqueLogin?) {
	}

	override fun isValid(login: String?, context: ConstraintValidatorContext?): Boolean {
		return login != null && !userRepository.containUsername(login);
	}
}