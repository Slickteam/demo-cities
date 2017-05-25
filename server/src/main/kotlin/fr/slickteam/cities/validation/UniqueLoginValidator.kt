package fr.slickteam.cities.validation

import fr.slickteam.cities.service.IUserService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Created by jguidoux on 23/05/2017.
 */

class UniqueLoginValidator(val userService: IUserService) : ConstraintValidator<UniqueLogin, String> {


	override fun initialize(constraintAnnotation: UniqueLogin?) {
	}

	override fun isValid(login: String?, context: ConstraintValidatorContext?): Boolean {
		return login != null && !userService.containUsername(login);
	}
}