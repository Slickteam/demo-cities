package fr.slickteam.cities.web

import fr.slickteam.cities.service.ICityService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


/**
 * Created by jguidoux on 05/05/2017.
 */
@Controller
class HomeController(val repository: ICityService) {


	@GetMapping("/cities")
	fun getCity(@RequestParam cityName: String, model: Model): String {
		val city = repository.getCity(cityName)
		model.addAttribute("city", city)
		return "displayCity"
	}


}


