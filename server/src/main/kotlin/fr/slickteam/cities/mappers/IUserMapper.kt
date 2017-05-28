package fr.slickteam.cities.mappers

import fr.slickteam.cities.model.Role
import fr.slickteam.cities.model.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional


/**
 * Created by jguidoux on 22/05/2017.
 */
@Mapper
@Transactional(propagation = Propagation.MANDATORY)
interface IUserMapper {
	fun addNewUser(user: User)
	fun findByUsername(username: String): User?
	fun findByEmail(email: String): User?
	fun deleteUser(login: String)
	fun countUsers(): Int

	fun getRole(roleName: String): Role
	fun attacheUser(@Param("user") User: User, @Param("role") Role: Role)
}