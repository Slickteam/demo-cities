package fr.slickteam.cities.validation

import fr.slickteam.cities.service.IUserService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Created by jguidoux on 23/05/2017.
 */

class UniqueEmailValidator(val userService: IUserService) : ConstraintValidator<UniqueEmail, String> {


	override fun initialize(constraintAnnotation: UniqueEmail?) {
	}

	override fun isValid(email: String?, context: ConstraintValidatorContext?): Boolean {
		return email != null && !userService.containEmail(email);
	}
}