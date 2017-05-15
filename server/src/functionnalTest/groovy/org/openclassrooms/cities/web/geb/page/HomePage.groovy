package org.openclassrooms.cities.web.geb.page
/**
 * class to manipulate the login page with Gebs
 *
 * Created by jguidoux on 12/05/2017.
 */
class HomePage extends TemplatePage {

    static url = "/"

    static at = { title == "Home page" }
    static content = {
        logoutForm { $("#form-logout") }
        submitLogoutButton { logoutForm.find("input", type: "submit") }

        searchForm { $("#search-form") }
        cityInput { searchForm.cityName() }
        submitSearchButton { searchForm.find("input", type: "submit") }
        citiesDiv(wait: true) { $("#results>div") }
        citiesNameDiv(wait: true) {
            citiesDiv.find("div", class: "cityName")
                    .allElements()
                    .collect { it.text }
        }


    }

    void logout() {
        submitLogoutButton.click()
    }

    void setCityName(String cityName) {
        cityInput << cityName
//        citiesDiv = $("#results .cityName")

    }

    void search() {
        submitSearchButton.click()
    }


}
