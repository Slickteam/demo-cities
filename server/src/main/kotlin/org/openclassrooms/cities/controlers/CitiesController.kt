package org.openclassrooms.cities.controlers

import org.openclassrooms.cities.model.City
import org.openclassrooms.cities.repositories.ICitiesRepository
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


/**
 * Created by jguidoux on 21/04/2017.
 * this controller produce rest api to access cities service
 */
@RestController
@RequestMapping("/cities")
@CrossOrigin(origins = arrayOf("http://localhost:63342"))
class CitiesController (var repository: ICitiesRepository){



    @RequestMapping("/list")
    fun getCities() : List<City> {
        val cities = repository.listCities()
        return cities
    }

    @RequestMapping("/filter")
    fun getCities(@RequestParam(value = "startWith") start: String) : List<City> {
        val cities = repository.filterCities(start)
        return cities
    }
}