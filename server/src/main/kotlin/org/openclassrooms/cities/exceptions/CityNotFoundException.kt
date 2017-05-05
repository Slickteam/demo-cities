package org.openclassrooms.cities.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

/**
 * Created by jguidoux on 05/05/2017.
 *
 * This exception is launch when a city is not found in the repository
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="City Not Found") //404
class CityNotFoundException(cityName: String?) : Exception("City $cityName not found.") {

    private val serialVersionUID = -3332292346834265371L
}