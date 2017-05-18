package org.openclassrooms.cities.web.geb

import org.openclassrooms.cities.web.geb.page.HomePage
import org.openclassrooms.cities.web.geb.page.LoginPage
import spock.lang.Ignore

/**
 * Created by jguidoux on 12/05/2017.
 * this is functionnal test to check login/logout
 * and security functionalies
 */
class LoginAccessPage extends BaseGebsSpec {

//    @Ignore
    def "login with valid user in the login page"() {

        when: "I try to to go the login page"
        go "/"
//        LoginPage loginPage = to(LoginPage)
        then: "I should be to the login page"
        def loginPage = at LoginPage

        when: "I log in with valid identifiers user:password"
        loginPage.login("user", "password")
        then: "I should be on the home page"
        at HomePage

        cleanup:
        logout()


    }


    @Ignore
    def "login with invalid user in the login page"() {

        when: "I try to to go the login page"
        go "/"
//        LoginPage loginPage = to(LoginPage)
        then: "I should be to the login page"
        def loginPage = at LoginPage

        when: "I log in with invalid identifiers user:bad-password"
        loginPage.login("user", "bad-password")


        then: "I still should still be on the login page"
        at LoginPage
        loginPage.with {
            and: "there should be 1 error message"
            errors.size() == 1
            and: "the message should say me that my username or my password is wrong"
            invalidUsernameOrPasswordError.present
        }

    }

    @Ignore
    def "try to access home page without being logged"() {
        when: "I access to the '/' url without being logged"
        go "/"
        then: "I should be on the login page"
        at LoginPage
    }

//    @Ignore
    def "try to logout"() {

        when: "I try to to go the login page"
        go "/"
//        LoginPage loginPage = to(LoginPage)
        then: "I should be to the login page"
        def loginPage = at LoginPage

        when: "I log in with valid identifiers user:password"
        loginPage.login("user", "password")
        then: "I should be on the home page"
        at HomePage

//        when: "I click on the logout button"
//        homePage.logout()
//
//        then: "I should be on back on the login page"
//        def loginPageResult = at LoginPage
//        loginPageResult.with {
//            and: "there should be 1 success message"
//            successes.size() == 1
//            and: "the message should say me that I'm successful logout"
//            SuccessLogoutMessage.present
//        }
    }


}
