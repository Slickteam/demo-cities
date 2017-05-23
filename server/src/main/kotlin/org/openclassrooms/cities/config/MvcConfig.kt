package org.openclassrooms.cities.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Created by jguidoux on 24/05/2017.
 */

@Configuration
class MvcConfig : WebMvcConfigurerAdapter() {

	override fun addViewControllers(registry: ViewControllerRegistry) {
		registry.addViewController("/").setViewName("index")
		registry.addViewController("/login").setViewName("login")
	}

}