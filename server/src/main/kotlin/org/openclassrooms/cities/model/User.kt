package org.openclassrooms.cities.model

import org.hibernate.validator.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by jguidoux on 19/05/2017.
 */
data class User(
        @get:NotNull
        @get:Size(min = 3, max = 12)
        var login: String = "",
        @NotNull
        @Email
        var email: String = "",
        @NotNull
        @Size(min = 5)
        var password: String = "")
