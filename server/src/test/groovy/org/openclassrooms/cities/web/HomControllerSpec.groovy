package org.openclassrooms.cities.web

import org.spockframework.compiler.model.Spec
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.util.UriComponentsBuilder
import spock.lang.Specification

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

/**
 * Created by jguidoux on 09/05/2017.
 */
class HomControllerSpec extends  Specification {

    HomeController homeController = new HomeController()

    MockMvc mockMvc

    def setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build()
    }

    def "go to home page"() {


        when: "I request the '/'"
        def perform = mockMvc.perform(MockMvcRequestBuilders.get("/"))
        def response = perform.andReturn().response


        then: "the request status should be OK (200)"
        response.status == HttpStatus.OK.value()

        and: "the retun view name should be 'index"
        perform.andExpect(MockMvcResultMatchers.view().name("index"))
    }


    def "display city"() {


        when: "I want to display the city of Rennes with uls '/cities?name=Rennes'"
        URI url = UriComponentsBuilder.fromUriString("/cities").
                queryParam("cityName", "Rennes").build().toUri()

        def perform = mockMvc.perform(MockMvcRequestBuilders.get(url))
        def response = perform.andReturn().response


        then: "the request status should be OK (200)"
        response.status == HttpStatus.OK.value()

        and: "the retun view name should be 'displayCity"
        perform.andExpect(MockMvcResultMatchers.view().name("displayCity"))

        and: "the model should contain Rennes information"
                perform.andExpect(MockMvcResultMatchers.model().attribute("city",
                allOf(
                        hasProperty("name", is("Rennes")),
                        hasProperty("population", is(100000)),
                        hasProperty("revenuPerHabitants", is(20000))
                )
                ))
    }
}
