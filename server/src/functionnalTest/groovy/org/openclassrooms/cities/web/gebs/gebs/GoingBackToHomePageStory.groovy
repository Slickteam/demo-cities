package org.openclassrooms.cities.web.gebs.gebs

import org.openclassrooms.cities.web.gebs.gebs.page.DisplayCityPage
import org.openclassrooms.cities.web.gebs.gebs.page.HomePage
import org.openclassrooms.cities.web.gebs.gebs.page.LoginPage

/**
 * Created by jguidoux on 14/05/2017.
 */
class GoingBackToHomePageStory extends BaseGebsSpec {

    def setup() {
        def loginPage = to LoginPage
        loginPage.login("user", "password")
    }

    def "going back to home page from nav bar"() {

        when: "going to DisplayCity page"
        to DisplayCityPage
        then: "I should be en DisplayCity page"
        def displayCityPage = at DisplayCityPage

        when: "I click on the home link"
        displayCityPage.goHome()

        then: "I shoud be on the home page"
        at HomePage

    }
}
