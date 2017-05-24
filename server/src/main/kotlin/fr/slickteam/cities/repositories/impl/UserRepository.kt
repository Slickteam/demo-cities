package fr.slickteam.cities.repositories.impl

import fr.slickteam.cities.model.User
import fr.slickteam.cities.repositories.IUserRepository
import org.springframework.stereotype.Repository

/**
 * Created by jguidoux on 22/05/2017.
 */
@Repository
class UserRepository : IUserRepository {



	val users = mutableListOf<User>()


	override fun addNewUser(user: User) {
		users.add(user)
	}

	override fun findByUsername(username: String?): User?
			= users.find { user -> user.login.equals(username) }


	override fun containUsername(login: String): Boolean
			= users.any { user -> user.login.equals(login) }

	override fun containEmail(email: String): Boolean
			= users.any { user -> user.email.equals(email) }
}