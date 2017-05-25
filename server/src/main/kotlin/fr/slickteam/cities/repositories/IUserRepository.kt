package fr.slickteam.cities.repositories

import fr.slickteam.cities.model.User
import org.apache.ibatis.annotations.Mapper


/**
 * Created by jguidoux on 22/05/2017.
 */
@Mapper
interface IUserRepository {
	fun addNewUser(user: User)
	fun findByUsername(username: String?): User?
	fun findByEemail(email: String): Boolean
}