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
        loginPage.with {
            username = "user"
            password = "password"
            submitButton.click()
        }

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
            username = "user"
            password = "bad-password"
            submitButton.click()

        }

        then:
        at LoginPage
        loginPage.with {
            errors.size() == 1
            invalidUsernameOrPasswordError.present
        }


    }
}
