package org.openclassrooms.cities.utils

import org.openclassrooms.cities.Application
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spockmvc.SpockMvcSpec

/**
 * Created by jguidoux on 04/05/2017.
 */
@ContextConfiguration(
       // loader = SpringApplicationContextLoader,
        classes = [Application]
)
class AbstractMvcSpec extends SpockMvcSpec{

    @Override
    MockMvc buildMockMvc(WebApplicationContext wac) {
        MockMvcBuilders.webAppContextSetup(wac).build()
    }
}
