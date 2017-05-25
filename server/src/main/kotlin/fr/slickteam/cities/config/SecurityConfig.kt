package fr.slickteam.cities.config

import fr.slickteam.cities.security.AuthenticationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


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
						"/api/rest/**", "/login", "/signup", "/logout", "/myconsole/**").permitAll()
				.antMatchers("/", "/**").hasRole("USER")
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/", true)

		http.csrf().disable();
		http.headers().frameOptions().disable();

	}

	@Throws(Exception::class)
	override fun configure(auth: AuthenticationManagerBuilder) {
		auth.authenticationProvider(authProvider());
		auth.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER")
	}

	@Bean
	fun authProvider(): DaoAuthenticationProvider {
		val authProvider = DaoAuthenticationProvider()
		authProvider.setUserDetailsService(userDetailsService)
		authProvider.setPasswordEncoder(passwordEncoder())
		return authProvider
	}


	@Bean
	fun passwordEncoder(): PasswordEncoder {
		return BCryptPasswordEncoder()
	}

}
