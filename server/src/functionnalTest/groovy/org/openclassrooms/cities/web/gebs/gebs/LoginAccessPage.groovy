package org.openclassrooms.cities.web.gebs.gebs

import org.openclassrooms.cities.web.gebs.gebs.page.HomePage
import org.openclassrooms.cities.web.gebs.gebs.page.LoginPage
import spock.lang.Ignore

/**
 * Created by jguidoux on 12/05/2017.
 */
class LoginAccessPage extends BaseGebsSpec {

    def "login with valid user in the login page"() {

        when:
        LoginPage loginPage = to(LoginPage)
        then:
        at LoginPage

        when:
        loginPage.login("user", "password")

        then:
        at HomePage


    }


    def "login with invalid user in the login page"() {

        when:
        LoginPage loginPage = to(LoginPage)
        then:
        at LoginPage

        when:
        loginPage.with {
            usernameInputField = "user"
            passwordInputField = "bad-password"
            submitButton.click()

        }

        then:
        at LoginPage
        loginPage.with {
            errors.size() == 1
            invalidUsernameOrPasswordError.present
        }

    }

    def "try to access home page without being logged"() {
        when:
        to HomePage
        then:
        thrown AssertionError
        and:
        at LoginPage
    }


    @Ignore
    def "try to access home page when I'm logged"() {
        when:
        to HomePage
        then:
        at HomePage
    }
}
