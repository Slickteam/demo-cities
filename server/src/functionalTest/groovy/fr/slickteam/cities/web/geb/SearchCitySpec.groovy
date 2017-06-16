package fr.slickteam.cities.web.geb

import fr.slickteam.cities.web.geb.page.DisplayCityPage
import fr.slickteam.cities.web.geb.page.ErrorPage
import fr.slickteam.cities.web.geb.page.HomePage

/**
 * Created by jguidoux on 14/05/2017.
 * This test check if the search city works well
 */
class SearchCitySpec extends BaseGebsSpec {


    def setup() {
        login()

    }

    def cleanup() {
        logout()
    }

    def "check the autocompletion system when looking for a city"() {

        given: "I am connected"
        and: "I am at home page"
        to HomePage
        def homePage = at HomePage
        and: "this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"

        when: "I write 'Re' on the input field"
        homePage.setCityName('Re')

        then: "the autocompletions system should show 2 cities"
        homePage.citiesDiv.size() == 2
        and: "These cities should be 'Rennes' and 'Reims'"
        homePage.citiesNameDiv == ['Rennes', 'Reims']

    }

    def "try to display an existing city"() {

        given: "I am connected"
        and: "I am at home page"
        to HomePage
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

        given: "I am connected"
        and: "I am at home page"
        to HomePage
        def homePage = at HomePage
        and: "this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"


        when: "I write 'Re' on the input field"
        homePage.setCityName('Guérande')

        and: "I click on she search button"
        homePage.search()

        then: "I should be on the error page"
        def errorPage = at ErrorPage
        and: "The error message should be : City 'Guérande' not found"
        errorPage.errorMessage == "Error : City 'Guérande' not found."


    }
}
