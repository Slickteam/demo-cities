package org.openclassrooms.cities.web.geb.page

import geb.Page

/**
 * Created by jguidoux on 14/05/2017.
 */
class ErrorPage extends Page {

    static url = "/error"

    static at = { title == "Error page" }

    static content = {
        errorMessage { $("#errorMessage").text() }
    }
}
