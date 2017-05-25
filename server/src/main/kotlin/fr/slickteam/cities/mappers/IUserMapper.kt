package fr.slickteam.cities.mappers

import fr.slickteam.cities.model.User
import org.apache.ibatis.annotations.Mapper


/**
 * Created by jguidoux on 22/05/2017.
 */
@Mapper
interface IUserMapper {
	fun addNewUser(user: User)
	fun findByUsername(username: String?): User?
	fun findByEmail(email: String): User?
	fun deleteUser(login: String)
}