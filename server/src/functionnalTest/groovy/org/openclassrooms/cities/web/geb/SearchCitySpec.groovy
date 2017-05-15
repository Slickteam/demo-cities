package org.openclassrooms.cities.web.geb

import org.openclassrooms.cities.web.geb.page.DisplayCityPage
import org.openclassrooms.cities.web.geb.page.ErrorPage
import org.openclassrooms.cities.web.geb.page.HomePage
import org.openclassrooms.cities.web.geb.page.LoginPage

/**
 * Created by jguidoux on 14/05/2017.
 * This test check if the search city works well
 */
class SearchCitySpec extends BaseGebsSpec {


    def setup() {
        def loginPage = to LoginPage
        loginPage.login("user", "password")
    }

    def "check the autocompletion system when looking for a city"() {

        given: "I am at home page"
        def homePage = at HomePage
        and: "this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"

        when: "I write 'Re' on the input field"
        homePage.setCityName('Re')

        then: "the autocompletions system should show 2 cities"
        homePage.citiesDiv.size() == 2
        and: "These cities should be 'Rennes' and 'Reims'"
        waitFor { homePage.citiesNameDiv == ['Rennes', 'Reims'] }

    }

    def "try to display an existing city"() {

        given: "I am at home page"
        def homePage = at HomePage
        and: "this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"


        when: "I write 'Re' on the input field"
        homePage.setCityName('Rennes')

        and: "I click on she search button"
        homePage.search()

        then: "I should be on the display city page"
        def displaypage = at DisplayCityPage
        and: "The city name should be 'Rennes'"
        displaypage.cityName == 'Rennes'
        and: "The population should be 100000"
        displaypage.population == 100000
        and: " The average revenue par habitants should be 20000€/hab"
        displaypage.revenue == "20000 €/hab"

    }


    def "try to display an not existing city"() {

        given: "I am at home page"
        def homePage = at HomePage
        and: "this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"


        when: "I write 'Re' on the input field"
        homePage.setCityName('Guérande')

        and: "I click on she search button"
        homePage.search()

        then: "I should be on the error page"
        def errorPage = at ErrorPage
        and: "The error message should be : City 'Guérande' not found"
        errorPage.errorMessage == "City 'Guérande' not found."


    }
}
