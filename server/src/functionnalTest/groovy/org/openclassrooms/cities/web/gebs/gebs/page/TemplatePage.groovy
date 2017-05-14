package org.openclassrooms.cities.web.gebs.gebs.page

import geb.Page

/**
 * Created by jguidoux on 14/05/2017.
 */
class TemplatePage extends Page {

    static content = {
        homeLink { $("#go-home") }
    }

    void goHome() {
        homeLink.click()
    }
}
