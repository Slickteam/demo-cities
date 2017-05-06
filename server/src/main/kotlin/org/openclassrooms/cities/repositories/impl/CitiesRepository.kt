package org.openclassrooms.cities.repositories.impl

import org.openclassrooms.cities.model.City
import org.openclassrooms.cities.repositories.ICitiesRepository
import org.openclassrooms.cities.utils.loadFromClassPath
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import java.nio.file.Files
import java.nio.file.Paths
import javax.annotation.PostConstruct


/**
 * Created by jguidoux on 21/04/2017.
 * this repository can read cities database to get information on it
 */
@Repository
class CitiesRepository : ICitiesRepository {

    final val filePath : String

    lateinit var cities: List<City>

    //---------------------------------------------------------------------------------------
    //MARK: - Constructors
    //---------------------------------------------------------------------------------------
    @Autowired constructor(@Value("\${base_path}") databaseFilePath: String) {
        this.filePath = databaseFilePath
    }


    @PostConstruct
    fun setup() {
        cities = listOf()
//        Files.lines(Paths.get(filePath).loadFromClassPath()).forEach{
//            cityName -> cities += City(cityName)
//        }

        Files.newBufferedReader(Paths.get(filePath).loadFromClassPath()).use {
            it.lines().forEach() {  cityName -> cities += City(cityName) }
        }

    }


    override fun listCities(): List<City> {
        return  cities
    }


    override fun filterCities(cityNameStartWith: String): List<City> {
        return cities.filter { city -> city.name.toLowerCase().startsWith(cityNameStartWith.toLowerCase()) }
    }


}
