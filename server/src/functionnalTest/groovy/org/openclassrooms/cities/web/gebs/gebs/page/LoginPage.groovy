package org.openclassrooms.cities.web.gebs.gebs.page

import geb.Page

/**
 * class to manipulate the login page with Gebs
 *
 * Created by jguidoux on 12/05/2017.
 */
class LoginPage extends Page {

    static url = "/login"

    static at = { title == "Login Page" }

    static content = {
        loginForm { $("#login-form") }
        username { loginForm.username() }
        password { loginForm.password() }
        submitButton {
            loginForm.find("input", type: "submit")
        }
    }

//    Page login(String userName, String password) {
//        nameField.value(userName)
//        passwordField.value(password)
//        loginButton.click()
//
//        return Browser.page
//    }
}
