package org.openclassrooms.cities.web

import org.openclassrooms.cities.exceptions.GlobalExceptionHandler
import org.openclassrooms.cities.model.City
import org.openclassrooms.cities.repositories.ICitiesRepository
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * Created by jguidoux on 09/05/2017.
 */
class HomeControllerSpec extends Specification {

    ICitiesRepository citiesRepository = Mock()
    HomeController homeController = new HomeController(citiesRepository)

    MockMvc mockMvc

    def setup() {
        this.mockMvc = standaloneSetup(homeController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
    }

    def "go to home page"() {

        when: "I request the '/'"
        def perform = mockMvc.perform(get("/"))
        def response = perform.andReturn().response


        then: "the request status should be OK (200)"
        response.status == HttpStatus.OK.value()

        and: "the retun view name should be 'index"
        perform.andExpect(view().name("index"))
    }


    def "display city"() {

        given: "this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"
        citiesRepository.filterCities("Rennes") >>
                [new City("Rennes", 1, 1),
                 new City("Reims", 1, 1)]


        when: "I want to display the city of Rennes with url '/cities?name=Rennes'"

        URI url = UriComponentsBuilder.fromUriString("/cities").
                queryParam("cityName", "Rennes").build().toUri()
        def perform = mockMvc.perform(get(url))
//                .param("cityName", "Rennes"))
        def response = perform.andReturn().response


        then: "the request status should be OK (200)"
        perform.andExpect(status().isOk())
        response.status == HttpStatus.OK.value()

        and: "the return view name should be 'displayCity"
        perform.andExpect(view().name("displayCity"))

        and: "the model should contain Rennes information"
        perform.andExpect(model().attribute("city",
                allOf(
                        hasProperty("name", is("Rennes")),
                        hasProperty("population", is(100000)),
                        hasProperty("revenuPerHabitants", is(20000))
                )
        ))
    }

    def "display city which does not exist"() {

        given: "this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"
        citiesRepository.filterCities("Rennnes") >> []


        when: "I want to display the city of 'Rennnes' with url '/cities?name=Rennnes'"

        URI url = UriComponentsBuilder.fromUriString("/cities").
                queryParam("cityName", "Rennnes").build().toUri()
        def perform = mockMvc.perform(get(url))
        def response = perform.andReturn().response


        then: "the request status should be OK (200)"
//        perform..andExpect(status().isOk())
        response.status == HttpStatus.OK.value()

        and: "the return view name should be 'GlobalErrorView'"
        perform.andExpect(view().name("error"))


    }


}
