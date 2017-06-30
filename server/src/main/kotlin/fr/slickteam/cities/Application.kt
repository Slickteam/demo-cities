package fr.slickteam.cities

import fr.slickteam.cities.model.City
import fr.slickteam.cities.service.ICityService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * Created by jguidoux on 21/04/2017.
 */
@SpringBootApplication
class Application {


	@Bean
	fun init(cityService: ICityService, @Value("\${app.base_path}") filePath: String)
			= CommandLineRunner {

		println()
		println()
		println()
		println("=================================")
		println("initialisation of the application")
		println("=================================")
		println()
		println()
		println()
		val inputStream = ClassPathResource(filePath).inputStream

		BufferedReader(InputStreamReader(inputStream)).use {
			it.lines().forEach() { cityName -> cityService.insertCity(City(name = cityName)) }
		}

	}
}

fun main(args: Array<String>) {


	SpringApplication.run(Application::class.java, *args)
}


@Configuration
@EnableSwagger2
class SwaggerConfig {
	@Bean
	fun api(): Docket {
		return Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
	}
}
