package org.openclassrooms.cities.exceptions

import com.google.common.base.Throwables
import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.context.request.WebRequest
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController




/**
 * Created by jguidoux on 09/05/2017.
 */


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "City Not Found") //404
class CityNotFoundException(cityName: String?) : Exception("City $cityName not found.") {

    private val serialVersionUID = -3332292346834265371L
}

@ControllerAdvice
internal class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception::class)
    fun exception(exception: Exception, model: Model): String {
        model.addAttribute("errorMessage", exception.message)
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
