package fr.slickteam.cities.web.geb

import fr.slickteam.cities.web.geb.page.DisplayCityPage
import fr.slickteam.cities.web.geb.page.HomePage
import fr.slickteam.cities.web.geb.page.TemplatePage
import spock.lang.Unroll

/**
 * Created by jguidoux on 14/05/2017.
 */
class GoingBackToHomePageStory extends BaseGebsSpec {

    def setup() {
        login()
    }

    @Unroll
    def "going back to home page from nav bar"(Class<TemplatePage> originPage) {

        when: "going to #originPage page"
        to originPage
        then: "I should be en #originPage page"
        def currentPage = at originPage

        when: "I click on the home link"
        currentPage.goHome()

        then: "I shoud be on the home page"
        at HomePage

        where:
        originPage      | _
        DisplayCityPage | _
        HomePage        | _

    }
}
