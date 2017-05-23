package org.openclassrooms.cities.config

import org.openclassrooms.cities.security.AuthenticationService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * Created by jguidoux on 24/05/2017.
 */
@Configuration
@EnableWebSecurity
class SecurityConfig(val userDetailsService: AuthenticationService) : WebSecurityConfigurerAdapter() {

	@Throws(Exception::class)
	override fun configure(http: HttpSecurity) {
		http
				.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/images/**",
						"/api/rest/**", "/login", "/signup", "/logout").permitAll()
				.antMatchers("/", "/**").hasRole("USER")
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/", true)


	}

//	@Autowired
//	@Throws(Exception::class)
//	fun configureGlobal(auth: AuthenticationManagerBuilder) {
//		auth
//				.inMemoryAuthentication()
//				.withUser("user").password("password").roles("USER")
//
//	}

	@Throws(Exception::class)
	override fun configure(auth: AuthenticationManagerBuilder?) {
		auth!!.userDetailsService(userDetailsService)
	}


//	@Bean
//	fun authenticationProvider(): DaoAuthenticationProvider {
//		val authProvider = DaoAuthenticationProvider()
//		authProvider.setUserDetailsService(userDetailsService)
////        authProvider.setPasswordEncoder(encoder())
//		return authProvider
//	}
//
//	@Bean
//	fun encoder(): PasswordEncoder {
//		return BCryptPasswordEncoder(11)
//	}

}
