package fr.slickteam.cities.service.impl

import fr.slickteam.cities.dto.Account
import fr.slickteam.cities.exceptions.UserAlreadyExisteException
import fr.slickteam.cities.mappers.IUserMapper
import fr.slickteam.cities.model.User
import fr.slickteam.cities.service.IUserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional


/**
 * Created by jguidoux on 24/05/2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
class UserService(val userMapper: IUserMapper,
                  val passwordEncoder: PasswordEncoder) : IUserService {


	@Throws(UserAlreadyExisteException::class)
	override fun registerNewUserAccount(user: Account) {


		val passwordEncoded = passwordEncoder.encode(user.password)
		val model = User(login = user.login, email = user.email, encodedPassword = passwordEncoded)
		val role = userMapper.getRole(roleName = "ROLE_USER")
		val role2 = userMapper.getRole(roleName = "ROLE_ACTUATOR")
		model.roles.add(role)
		if (existUser(user)) {
			throw UserAlreadyExisteException()
		}
		userMapper.addNewUser(model)
		userMapper.attacheUser(model, role)
		userMapper.attacheUser(model, role2)
	}


	fun existUser(user: Account): Boolean =
			containUsername(user.login) || containEmail(user.email)

	override fun containUsername(login: String): Boolean {
		return userMapper.findByUsername(login) != null
	}


	override fun containEmail(email: String): Boolean {
		return userMapper.findByEmail(email) != null
	}

	override fun deleteUser(user: Account) {
		userMapper.deleteUser(user.login)
	}

}