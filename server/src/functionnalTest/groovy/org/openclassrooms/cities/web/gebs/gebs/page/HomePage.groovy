package org.openclassrooms.cities.web.gebs.gebs.page

import geb.Page

/**
 * class to manipulate the login page with Gebs
 *
 * Created by jguidoux on 12/05/2017.
 */
class HomePage extends Page {

    static url = "/"

    static at = { title == "Home page" }
    static content = {
        loginForm { $("#form-logout") }
        submitButton {
            loginForm.find("input", type: "submit")
        }

        successes(required: false) { $(".success") }

        SuccessLogoutMessage(required: false) {

            successes.filter(text: contains("You have been successful logged out"))

        }
    }

    void logout() {
        submitButton.click()
    }


}
