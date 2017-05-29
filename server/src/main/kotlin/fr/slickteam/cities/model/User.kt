package fr.slickteam.cities.model

import fr.slickteam.cities.validation.UniqueEmail
import fr.slickteam.cities.validation.UniqueLogin
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty
import java.io.Serializable
import javax.validation.constraints.Size

/**
 * Created by jguidoux on 24/05/2017.
 */
data class User(

		var id: Int? = null,

		@get:NotEmpty
		@get:Size(min = 3, max = 12)
		@get:UniqueLogin
		val login: String = "",

		@get:NotEmpty
		@get:Email
		@get:Size(max = 40)
		@get:UniqueEmail
		val email: String = "",

		@get:NotEmpty
		@get:Size(min = 5, max = 40)
		val encodedPassword: String = "",

		val roles: MutableList<Role> = mutableListOf()) {

	constructor(id: Int, login: String, email: String, encodedPassword: String)
			: this(id, login, email, encodedPassword, mutableListOf())


}

data class Role(var id: Int? = null, var roleName: String) : Serializable