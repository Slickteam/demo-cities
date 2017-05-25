package fr.slickteam.cities.repositories.impl

import fr.slickteam.cities.exceptions.CityNotFoundException
import fr.slickteam.cities.model.City
import fr.slickteam.cities.repositories.ICitiesRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.annotation.PostConstruct


/**
 * Created by jguidoux on 21/04/2017.
 * this repository can read cities database to get information on it
 */
@Repository
class CitiesRepository(
		@Value("\${base_path}")
		databaseFilePath: String) : ICitiesRepository {


    final val filePath: String = databaseFilePath

    lateinit var cities: List<City>


    @PostConstruct
    fun setup() {
        cities = listOf()
        val resource = ClassPathResource(filePath)
        val inputStream = resource.getInputStream()

        BufferedReader(InputStreamReader(inputStream)).use {
            it.lines().forEach() { cityName -> cities += City(cityName) }
        }

    }


    override fun listCities(): List<City> {
        return cities
    }


    override fun filterCities(cityNameStartWith: String): List<City> {
        return cities.filter { city -> city.name.toLowerCase().startsWith(cityNameStartWith.toLowerCase()) }
    }


    override fun getCity(cityName: String): City {
        val result = cities.filter { city -> city.name.toLowerCase() == cityName.toLowerCase() }
        return when (result.size) {
            0 -> throw CityNotFoundException(cityName)
            1 -> result[0]
            else -> throw InternalError("database is corrupted. a city name shoulp appear only one, but " +
                    "$cityName appear ${result.size} times")
        }
    }
}
