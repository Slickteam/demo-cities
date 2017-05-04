package org.openclassrooms.cities.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

/**
 * Created by jguidoux on 02/05/2017.
 * test list cities from the Rest API
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:/env-test.properties")
class ListCitiesFromRestSpeck extends  Specification{

    @Autowired
    private TestRestTemplate restTemplate

    def "list cities from rest api '/citie/list"(){
        given:
        when:
        def cities = this.restTemplate.getForObject("/cities/list", List)

        then:
        cities.collect{it.name}.size() == 4
    }


}
