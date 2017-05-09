package org.openclassrooms.cities.web

import org.openclassrooms.cities.exceptions.CityNotFoundException
import org.openclassrooms.cities.model.City
import org.openclassrooms.cities.repositories.ICitiesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


/**
 * Created by jguidoux on 05/05/2017.
 */
@Controller
class HomeController(var repository: ICitiesRepository) {

    @Autowired


    @GetMapping("/")
    fun index(): String {
        return "index"
    }


    @GetMapping("/cities")
    fun getCity(@RequestParam cityName: String, model: Model): String {
        val city = repository.getCity(cityName)
        model.addAttribute("city", city)
        return "displayCity"
    }

//    @ExceptionHandler
//    fun handleException(exception: CityNotFoundException): String {
//        return "errorView"
//    }
}