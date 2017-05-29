package fr.slickteam.cities

import fr.slickteam.cities.model.City
import fr.slickteam.cities.service.ICityService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader


/**
 * Created by jguidoux on 21/04/2017.
 */
@SpringBootApplication
@PropertySource("classpath:/env.properties")
class Application {


	@Bean
	fun init(cityService: ICityService, @Value("\${base_path}") filePath: String)
			= CommandLineRunner {

		println()
		println()
		println()
		println("============================")
		println("initialisation of the application")
		println("============================")
		println()
		println()
		println()
		val inputStream = Thread.currentThread().contextClassLoader.getResourceAsStream(filePath) ?:
				throw FileNotFoundException("file '$filePath' does not exist!")

		BufferedReader(InputStreamReader(inputStream)).use {
			it.lines().forEach() { cityName -> cityService.insertCity(City(name = cityName)) }
		}

	}
}

fun main(args: Array<String>) {


	SpringApplication.run(Application::class.java, *args)
}

