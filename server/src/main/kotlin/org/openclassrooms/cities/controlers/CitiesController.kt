package org.openclassrooms.cities.controlers

import org.openclassrooms.cities.model.City
import org.openclassrooms.cities.repositories.ICitiesRepository
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.awt.PageAttributes


/**
 * Created by jguidoux on 21/04/2017.
 * this controller produce rest api to access cities service
 */
@RestController
@RequestMapping("/cities")
@CrossOrigin(origins = arrayOf("http://localhost:63342"))
class CitiesController (var repository: ICitiesRepository){



    @RequestMapping(value = "/list",
            method = arrayOf(RequestMethod.GET),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getCities() : List<City> {
        val cities = repository.listCities()
        return cities
    }

    @RequestMapping("/filter",
            method = arrayOf(RequestMethod.GET),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getCities(@RequestParam(value = "startWith") start: String) : List<City> {
        val cities = repository.filterCities(start)
        return cities
    }

    @RequestMapping("/get",
            method = arrayOf(RequestMethod.GET),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getCity(@RequestParam(value = "name") name: String) : City {
        val cities = repository.filterCities(name)
        return cities[0]
    }
}