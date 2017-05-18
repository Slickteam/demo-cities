package org.openclassrooms.cities.web.geb.page

import geb.Page

/**
 * class to manipulate the login page with Gebs
 *
 * Created by jguidoux on 12/05/2017.
 */
class LoginPage extends Page {

    static url = "/login"

    static at = {
        title == "Login Page"
    }

    static content = {
        loginForm { $("#login-form") }
        usernameInputField { loginForm.username() }
        passwordInputField { loginForm.password() }
        submitButton() {
            loginForm.find("input", type: "submit")
        }


        errors(required: false) { $(".error") }

        invalidUsernameOrPasswordError(required: false) {
            errors.filter(text: contains("Invalid username or password"))
        }

        successes(required: false) { $(".success") }
        SuccessLogoutMessage(required: false) {
            successes.filter(text: contains("You have been successful logged out"))
        }


    }


    void login(String username, String password) {
        usernameInputField << username
        passwordInputField << password
        submitButton.click()

    }
}
