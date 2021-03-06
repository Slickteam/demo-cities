package fr.slickteam.cities.web.geb

import fr.slickteam.cities.web.geb.page.LoginPage

/**
 * Created by jguidoux on 18/05/2017.
 */
class AnnonymousPageSpec extends BaseGebsSpec {

    def "I should not be able to see the home link on a anonymous access page"() {
        given: "I am not connected"

        when: "I am on the login page"
        def loginpage = to LoginPage

        then: "The home link page should not be present"
        !loginpage.homeLink.displayed

    }


    def "I should not be able to see the logout link on a anonymous access page"() {
        given: "I am not connected"

        when: "I am on the login page"
        def loginpage = to LoginPage

        then: "The home link page should not be present"
        !loginpage.submitLogoutLink.displayed

    }


    def "I should not be able to see a username on a anonymous access page"() {
        given: "I am not connected"

        when: "I am on the login page"
        def loginpage = to LoginPage

        then: "The home link page should not be present"
        !loginpage.username.displayed

    }


}
