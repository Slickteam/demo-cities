package fr.slickteam.cities.security

import fr.slickteam.cities.mappers.IUserMapper
import fr.slickteam.cities.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional


/**
 * Created by jguidoux on 22/05/2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
class AuthenticationService(val userRepository: IUserMapper) : UserDetailsService {


	@Throws(UsernameNotFoundException::class)
	override fun loadUserByUsername(username: String): UserDetails {

		val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)
		return MyUserPrincipal(user)
	}
}


class MyUserPrincipal(val user: User) : UserDetails {

	override fun getUsername() = user.login


	override fun isCredentialsNonExpired(): Boolean {
		return true
	}

	override fun getPassword(): String {
		return user.encodedPassword
	}

	override fun isAccountNonExpired(): Boolean {
		return true
	}

	override fun isAccountNonLocked(): Boolean {
		return true
	}

	override fun isEnabled(): Boolean {
		return true
	}

	override fun getAuthorities(): MutableCollection<out GrantedAuthority> {

		val authorities = mutableListOf<GrantedAuthority>(SimpleGrantedAuthority("ROLE_USER"))
		return authorities

	}

}
