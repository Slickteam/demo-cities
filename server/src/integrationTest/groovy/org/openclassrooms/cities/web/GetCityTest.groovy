package org.openclassrooms.cities.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.util.UriComponentsBuilder
import spock.lang.Specification

import static org.hamcrest.Matchers.hasProperty
import static org.hamcrest.Matchers.is
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * Created by jguidoux on 12/05/2017.
 *
 * I test the mvc to get a city
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:/env-test.properties")
@WithMockUser
class GetCityTest extends Specification {

    @Autowired
    private MockMvc mockMvc

    def "try to get an existing city"() {

        given: "this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"
        and: "the url '/cities?cityName=Rennes'"
        def url = UriComponentsBuilder.fromUriString("/cities")
                .queryParam("cityName", "Rennes")
                .build().toUri()
        when: "I ask for this url"
        def request = this.mockMvc.perform(get(url))

        then: "the request status should be ok"
        request.andExpect(status().isOk())

        and: "the view name should be 'displayCity'"
        request.andExpect(view().name("displayCity"))

        and: "the model should have attribute 'city'"
        request.andExpect(model().attributeExists("city"))

        and: "the model city should have name 'Rennes'"
        request.andExpect(model().attribute("city",
                hasProperty("name", is("Rennes"))))
    }

    def "try to get a no existing city"() {
        given: "this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"
        and: "the url '/cities?cityName=Guérande'"
        def url = UriComponentsBuilder.fromUriString("/cities")
                .queryParam("cityName", "Guérande")
                .build().toUri()
        when: "I ask for this url"
        def request = this.mockMvc.perform(get(url))

        then: "the request status should be ok"
        request.andExpect(status().isOk())

        and: "the view name should be 'error'"
        request.andExpect(view().name("error"))

        and: "the model should have attribute 'errorMessage'"
        request.andExpect(model().attributeExists("errorMessage"))

        and: "the error message should be 'City 'Guérande' not found.'"
        request.andExpect(model().attribute("errorMessage",
                is("City 'Guérande' not found.")))
    }
}
