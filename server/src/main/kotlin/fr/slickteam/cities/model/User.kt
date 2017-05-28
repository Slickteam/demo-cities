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
		val login: String,

		@get:NotEmpty
		@get:Email
		@get:Size(max = 40)
		@get:UniqueEmail
		val email: String,

		@get:NotEmpty
		@get:Size(min = 5, max = 40)
		val encodedPassword: String

//		val roles: List<Role> = listOf()
) : Serializable


data class Role(val id: Int, val roleName: String) : Serializable