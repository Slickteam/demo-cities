package fr.slickteam.cities.web

import fr.slickteam.cities.dto.Account
import fr.slickteam.cities.service.IUserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

/**
 * Created by jguidoux on 24/05/2017.
 */
@Controller
class AccountController(val userService: IUserService) {

	@GetMapping("/signup")
	fun signup(model: Model): String {
		model.addAttribute("user", Account())
		return "signup"
	}

	@PostMapping("/signup")
	fun signup(@Valid @ModelAttribute("user") user: Account, results: BindingResult): String {
		if (results.hasErrors()) {
			return "signup"
		}
		userService.registerNewUserAccount(user)
		return "redirect:/login?signupSuccess"
	}
}