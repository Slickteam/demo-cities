package fr.slickteam.cities.dto

import fr.slickteam.cities.validation.UniqueEmail
import fr.slickteam.cities.validation.UniqueLogin
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty
import javax.validation.constraints.Size

/**
 * Created by jguidoux on 19/05/2017.
 */
data class Account(

		@get:NotEmpty
		@get:Size(min = 3, max = 12)
		@get:UniqueLogin
		var login: String = "",

		@get:NotEmpty
		@get:Email
		@get:Size(max = 40)
		@get:UniqueEmail
		var email: String = "",

		@get:NotEmpty
		@get:Size(min = 5, max = 40)
		var password: String = "")
