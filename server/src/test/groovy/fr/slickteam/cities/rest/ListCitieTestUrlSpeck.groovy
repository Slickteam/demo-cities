package fr.slickteam.cities.rest

import fr.slickteam.cities.model.City
import fr.slickteam.cities.service.ICityService
import groovy.json.JsonSlurper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by jguidoux on 03/05/2017.
 * this file test the rest api to get cities
 *
 * this unit test only show if the rest api /cities/list work
 *  call the CitiesController and return a list of cities in json format
 */
class ListCitieTestUrlSpeck extends Specification {


    ICityService citiesRepository = Mock()
    CitiesController citiesController = new CitiesController(citiesRepository)

    MockMvc mockMvc

    def setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(citiesController).build()
    }

    def "the request  '/api/rest/citie/list should return status 200"() {




        when: "I ask for the rest api '/api/rest/cities/list'"
        def perform = this.mockMvc.perform(get("/api/reste/cities/list").
                accept(MediaType.APPLICATION_JSON))


        then: "the request status should be 'ok'"
        perform.andExpect(status().isNotFound())

    }

    def "list cities from rest api '/api/rest/citie/list with json result"() {


        given:"this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"
        citiesRepository.listCities() >> [new City(null, "Paris", 1, 1),
                                          new City(null, "Rennes", 1, 1),
                                          new City(null, "Bordeaux", 1, 1),
                                          new City(null, "Reims", 1, 1)]

        when: "I ask for the rest api '/api/rest/cities/rest'"
        def response = this.mockMvc.perform(get("/api/rest/cities/list").
                accept(MediaType.APPLICATION_JSON)).andReturn().response
        def villes = new JsonSlurper().parseText(response.contentAsString)


        then: "the result should be a valid json content of the cites 'Paris' 'Rennes' 'Bordeaux' 'Reims'"
        response.status == OK.value()
        response.contentType == MediaType.APPLICATION_JSON_UTF8_VALUE
        villes.size() == 4
        villes.collect{it.name} == ['Paris', "Rennes", "Bordeaux", "Reims"]
    }



}

