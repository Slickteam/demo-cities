package fr.slickteam.cities.repositories

import fr.slickteam.cities.model.User


/**
 * Created by jguidoux on 22/05/2017.
 */
interface IUserRepository {
	fun addNewUser(user: User)
	fun findByUsername(username: String?): User?
	fun containUsername(login: String): Boolean
	fun containEmail(email: String): Boolean
}