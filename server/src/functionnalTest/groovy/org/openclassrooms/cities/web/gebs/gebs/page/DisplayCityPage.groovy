package org.openclassrooms.cities.web.gebs.gebs.page
/**
 * Created by jguidoux on 14/05/2017.
 */
class DisplayCityPage extends TemplatePage {

    static url = "/cities?cityName=Rennes"

    static at = { title == "Display city" }

    static content = {
        cityName { $("#cityName").text() }
        population { $("#population").text().toInteger() }
        revenue { $("#revenue").text() }
    }
}
