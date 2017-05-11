package org.openclassrooms.cities.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithAnonymousUser
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.util.UriComponentsBuilder
import spock.lang.Specification

import static org.hamcrest.Matchers.containsInAnyOrder
import static org.hamcrest.Matchers.hasItem
import static org.hamcrest.Matchers.hasSize
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * Created by jguidoux on 11/05/2017.
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:/env-test.properties")
class SecurityAccessSpeck extends Specification {

    @Autowired
    private MockMvc mockMvc

    @WithAnonymousUser
    def "try to go to home page without being logged"() {

        given:

        when: "I try to acces to the '/' url without being connected"
        def request = this.mockMvc.perform(get("/"))

        then: "the controller shoult return the 'login' page"
        request.andExpect(status().is3xxRedirection())
        request.andExpect(redirectedUrl("http://localhost/login"))

    }

    @WithAnonymousUser
    def "try to go to a display-city page without being logged"() {

        given: "the url '/cities?cityName=Rennes'"
        def url = UriComponentsBuilder.fromUriString("/cities")
                .queryParam("cityName", "Rennes")
                .build().toUri()

        when: "I try to access to this url without being connected"
        def request = this.mockMvc.perform(get(url))

        then: "the controller shoult return the 'login' page"
        request.andExpect(status().is3xxRedirection())
        request.andExpect(redirectedUrl("http://localhost/login"))

    }

    @WithAnonymousUser
    def "try to go to a rest api '/api/rest/cities/list' without being logged"() {


        when: "I try to access to this url without being connected"
        def request = this.mockMvc.perform(get("/api/rest/cities/list"))

        then: "the controller should work normally and return a list of cities"
        request.andExpect(status().isOk())

        and: "the result should be of json type"
        request.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

        and: "the result should be a list of 4"
        request.andExpect(jsonPath("\$", hasSize(4)))
        request.andExpect(jsonPath("\$[*].name",
                containsInAnyOrder("Rennes", "Paris", "Bordeaux", "Reims")))


    }


    @WithMockUser
    def "try to go to home page without right password"() {

        given:

        when: "I try to acces to the '/' url"
        def request = this.mockMvc.perform(get("/"))

        then: "the controller shoult return the 'login' page"
        request.andExpect(status().isOk())
        request.andExpect(view().name("index"))


    }
}
