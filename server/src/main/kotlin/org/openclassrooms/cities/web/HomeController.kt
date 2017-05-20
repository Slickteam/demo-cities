package org.openclassrooms.cities.web

import org.openclassrooms.cities.model.User
import org.openclassrooms.cities.repositories.ICitiesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid


/**
 * Created by jguidoux on 05/05/2017.
 */
@Controller
class HomeController(val repository: ICitiesRepository) {

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

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }


    @GetMapping("/signup")
    fun signup(model: Model): String {
        model.addAttribute("user", User())
        return "signup"
    }

    @PostMapping("/signup")
    fun signup(@Valid user: User, results: BindingResult): String {
        if (results.hasErrors()) {
            return "signup";
        }
        return "redirect:/login"
    }


}