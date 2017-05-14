package org.openclassrooms.cities.web.gebs.gebs

import org.openclassrooms.cities.web.gebs.gebs.page.HomePage
import org.openclassrooms.cities.web.gebs.gebs.page.LoginPage

/**
 * Created by jguidoux on 14/05/2017.
 */
class SearchCitySpec extends BaseGebsSpec {


    def "check the autocompletion system when looking for a city"() {

        given: "the database contains these cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims'"

        when: "I access to the '/' url without being logged"
        go "/"
        and: "I should first go to the login page to connect"
        at LoginPage
        and: "I enter valid identifiers"
        login("user", "password")
        then: "I should be on the home page"
        def homePage = at HomePage


        when: "I write 'Re' on the input field"
        homePage.setCityName('Re')

        then: "the autocompletions system should show 2 cities"
        homePage.citiesDiv.size() == 2
        and: "These cities should be 'Rennes' and 'Reims'"
        waitFor { homePage.citiesNameDiv == ['Rennes', 'Reims'] }

    }
}
