package org.openclassrooms.cities.web.gebs.gebs

import org.openclassrooms.cities.web.gebs.gebs.page.HomePage
import org.openclassrooms.cities.web.gebs.gebs.page.LoginPage

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
        loginPage.login("user", "bad-password")

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


    def "try to logout"() {
        when:
        go "/"
        and:
        at LoginPage
        and:
        login("user", "password")
        then:
        at HomePage

        when:
        logout()

        then:
        at LoginPage
    }
}
