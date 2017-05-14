package org.openclassrooms.cities.web.gebs.gebs

import geb.spock.GebReportingSpec
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
}
