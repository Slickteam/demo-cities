package org.openclassrooms.cities.web

import org.openclassrooms.cities.model.City
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam


/**
 * Created by jguidoux on 05/05/2017.
 */
@Controller
class HomeController {

    @GetMapping("/")
    fun index(): String {
        return "index"
    }


    @GetMapping("/cities")
    fun getCity(@RequestParam cityName: String, model: Model): String {
        model.addAttribute("city", City(cityName))
        return "displayCity"
    }
}