package org.openclassrooms.cities.web.geb

import org.openclassrooms.cities.web.geb.page.LoginPage

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


}
