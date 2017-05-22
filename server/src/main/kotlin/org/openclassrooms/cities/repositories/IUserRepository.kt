package org.openclassrooms.cities.repositories

import org.openclassrooms.cities.model.User

/**
 * Created by jguidoux on 22/05/2017.
 */
interface IUserRepository {
	fun addNewUser(user: User)
	fun findByUsername(username: String?): User?
}