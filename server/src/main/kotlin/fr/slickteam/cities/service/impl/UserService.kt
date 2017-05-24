package fr.slickteam.cities.service.impl

import fr.slickteam.cities.dto.Account
import fr.slickteam.cities.model.User
import fr.slickteam.cities.repositories.impl.UserRepository
import fr.slickteam.cities.service.IUserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


/**
 * Created by jguidoux on 24/05/2017.
 */
@Service
class UserService(val userRepository: UserRepository,
                  val passwordEncoder: PasswordEncoder) : IUserService {


//	init {
//		registerNewUserAccount(Account(login = "user", email = "foo@toto.fr", password = "password"))
//	}

	override fun registerNewUserAccount(user: Account) {


		val passwordEncoded = passwordEncoder.encode(user.password)
		val model = User(user.login, user.email, passwordEncoded)

		userRepository.addNewUser(model)
	}
}