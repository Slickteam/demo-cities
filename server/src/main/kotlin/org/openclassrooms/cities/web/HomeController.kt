package org.openclassrooms.cities.web

import org.openclassrooms.cities.model.City
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping



/**
 * Created by jguidoux on 05/05/2017.
 */
@Controller
class HomeController {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("ville", City("Rennes"))
        return "index"
    }
}