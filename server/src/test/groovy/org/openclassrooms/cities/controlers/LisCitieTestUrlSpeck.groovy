package org.openclassrooms.cities.controlers

import com.jayway.jsonpath.JsonPath
import groovy.json.JsonSlurper
import org.openclassrooms.cities.model.City
import org.openclassrooms.cities.repositories.ICitiesRepository
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

/**
 * Created by jguidoux on 03/05/2017.
 * this file test the rest api to get cities
 */
class LisCitieTestUrlSpeck extends Specification {


    ICitiesRepository citiesRepository = Mock() {
        listCities() >> [new City("Paris", 1, 1),
                         new City("Rennes", 1, 1),
                         new City("Bordeaux", 1, 1),
                         new City("Reims", 1, 1)]
    }
    CitiesController citiesController = new CitiesController(citiesRepository)

    MockMvc mockMvc

    def setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(citiesController).build()
    }

    def "list cities from rest api '/citie/list with json result"() {
        given:

        when:
        def response = this.mockMvc.perform(get("/cities/list").
                accept(MediaType.APPLICATION_JSON)).andReturn().response
        def villes = new JsonSlurper().parseText(response.contentAsString)


        then:
        response.status == OK.value()
        response.contentType == MediaType.APPLICATION_JSON_UTF8_VALUE
        villes.size() == 4
        villes.collect{it.name} == ['Paris', "Rennes", "Bordeaux", "Reims"]
    }



}

