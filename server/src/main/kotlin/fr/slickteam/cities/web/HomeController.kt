package fr.slickteam.cities.web

import fr.slickteam.cities.dto.Account
import fr.slickteam.cities.repositories.ICitiesRepository
import fr.slickteam.cities.service.IUserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid


/**
 * Created by jguidoux on 05/05/2017.
 */
@Controller
class HomeController(val repository: ICitiesRepository,
                     val userService: IUserService) {


	@GetMapping("/cities")
    fun getCity(@RequestParam cityName: String, model: Model): String {
        val city = repository.getCity(cityName)
        model.addAttribute("city", city)
        return "displayCity"
    }



    @GetMapping("/signup")
    fun signup(model: Model): String {
        model.addAttribute("user", Account())
        return "signup"
    }

    @PostMapping("/signup")
    fun signup(@Valid @ModelAttribute("user") user: Account, results: BindingResult): String {
        if (results.hasErrors()) {
            return "signup";
        }
        userService.registerNewUserAccount(user)
        return "redirect:/login?signupSuccess"
    }


}


