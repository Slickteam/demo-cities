package org.openclassrooms.cities.repositories.impl

import org.openclassrooms.cities.model.User
import org.openclassrooms.cities.repositories.IUserRepository
import org.springframework.stereotype.Repository

/**
 * Created by jguidoux on 22/05/2017.
 */
@Repository
class UserRepository : IUserRepository {



	val users = mutableListOf<User>()

	init {
		users.add(User(login = "user", email = "foo@toto.fr", password = "password"))
	}

	override fun addNewUser(user: User) {
		users.add(user)
	}

	override fun findByUsername(username: String?): User?
			= users.find { user -> user.login.equals(username) }


	override fun containUsername(login: String): Boolean
			= findByUsername(login) != null

	override fun containEmail(email: String): Boolean
			= users.find { user -> user.email.equals(email) } != null
}