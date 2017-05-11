package org.openclassrooms.cities.web

import org.openclassrooms.cities.repositories.ICitiesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam


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

    @RequestMapping("/login")
    fun login(): String {
        println("login")
        return "login"
    }

    @RequestMapping("/login-error")
    fun loginError(model: Model): String {
        model.addAttribute("loginError", true)
        println("Erreur de login")
        return "login"
    }

//    @ExceptionHandler
//    fun handleException(exception: CityNotFoundException): String {
//        return "errorView"
//    }
}