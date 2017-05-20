package org.openclassrooms.cities.web.geb

import geb.spock.GebReportingSpec
import org.openclassrooms.cities.web.geb.page.HomePage
import org.openclassrooms.cities.web.geb.page.LoginPage
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.TestPropertySource

/**
 * Created by jguidoux on 12/05/2017.
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:/env-test.properties")
class BaseGebsSpec extends GebReportingSpec {

    @Value('${local.server.port}')
    int port

    def setup() {
        browser.setBaseUrl("http://localhost:$port")
    }

    def login() {
        def loginPage = to LoginPage
        loginPage.login("user", "password")

    }

    def logout() {
        def homePage = to HomePage
        homePage.logout()
    }
}
