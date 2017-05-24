package fr.slickteam.cities.rest

import fr.slickteam.cities.model.City
import fr.slickteam.cities.repositories.ICitiesRepository
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


/**
 * Created by jguidoux on 21/04/2017.
 * this controller produce rest api to access cities service
 */
@RestController
@RequestMapping("/api/rest/cities")
@CrossOrigin(origins = arrayOf("http://localhost:63342"))
class CitiesController(val repository: ICitiesRepository) {


    /**
     * get the complete list of cities
     *
     * rest url : '/cities/list'
     */
    @RequestMapping(value = "/list",
            method = arrayOf(RequestMethod.GET),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getCities() : List<City> {
        val cities = repository.listCities()
        return cities
    }

    /**
     * get all cities starting by these letters
     *
     * rest url : 'cities/filter?startWith=value'
     *
     * @param start : the starting letter of asking cities
     * @return the list of desire cities
     *
     *
     */
    @RequestMapping("/filter",
            method = arrayOf(RequestMethod.GET),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getCities(@RequestParam(value = "startWith") start: String) : List<City> {
        val cities = repository.filterCities(start)
        return cities
    }

    /**
     * get a city corresponding to a name
     *
     * rest url : '/cities/get?name=value'
     *
     * @param name : the city nane
     * @return information of the asking city
     *
     * @throws CityNotFoundException if the city is not found.
     * In the case, an http request with status 404 is send
     * with message 'City not found.
     *
     * */
    @RequestMapping("/get",
            method = arrayOf(RequestMethod.GET),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getCity(@RequestParam(value = "name") name: String) : City {
        return repository.getCity(name)
    }
}