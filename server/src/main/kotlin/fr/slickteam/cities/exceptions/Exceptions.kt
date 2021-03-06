package fr.slickteam.cities.exceptions

import com.google.common.base.Throwables
import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.NoHandlerFoundException
import java.lang.Exception


/**
 * Created by jguidoux on 09/05/2017.
 */


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "City Not Found") //404
class CityNotFoundException(cityName: String?) : Exception("City '$cityName' not found.") {

	private val serialVersionUID = -3332292346834265371L
}

class UserAlreadyExisteException
	: Exception("Can't add this new User, because a user with same login or acount already exist")

@ControllerAdvice
internal class GlobalExceptionHandler {


	@ExceptionHandler(value = Exception::class)
	fun exception(exception: Exception, model: Model): String {
		println("exception launch")
		model.addAttribute("errorMessage", exception.message)
		return "error"
	}

	@ExceptionHandler(NoHandlerFoundException::class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	fun handleResourceNotFoundException(model: Model): String {
		println("page not found")
		model.addAttribute("errorMessage", "request page not found")
		return "error"
	}
}

@ControllerAdvice(annotations = arrayOf(RestController::class))
internal class ApiExceptionHandlerAdvice {

	/**
	 * Handle exceptions thrown by handlers.
	 */
	@ExceptionHandler(value = Exception::class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	fun exception(exception: Exception, request: WebRequest): ApiError {
		return ApiError(Throwables.getRootCause(exception).message)
	}
}

data class ApiError(val message: String?) {

}
