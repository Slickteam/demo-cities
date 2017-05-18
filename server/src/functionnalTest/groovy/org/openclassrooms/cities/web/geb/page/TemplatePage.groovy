package org.openclassrooms.cities.web.geb.page

import geb.Page

/**
 * Created by jguidoux on 14/05/2017.
 */
class TemplatePage extends Page {

    static content = {
        homeLink(required: false) { $("#go-home") }
        logoutForm(required: false) { $("#form-logout") }
        submitLogoutLink(required: false) { logoutForm.find("input", type: "submit") }

    }

    void goHome() {
        homeLink.click()
    }

    void logout() {
        submitLogoutLink.click()
    }
}
