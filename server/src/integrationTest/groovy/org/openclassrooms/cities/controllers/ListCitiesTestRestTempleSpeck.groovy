package org.openclassrooms.cities.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

/**
 * Created by jguidoux on 02/05/2017.
 * test list cities from the Rest API
 *
 * integration test to test the rest api /cities/list
 * This api should get the complete list of cities contains in the servert
 *
 * this test show the use of the TestRestTemplate provided by Spring-boot
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:/env-test.properties")
class ListCitiesTestRestTempleSpeck extends  Specification{

    @Autowired
    private TestRestTemplate restTemplate

    def "list cities from rest api '/citie/list with json result"() {

        given:"this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"

        when: "I ask for the rest api '/cities/rest'"
        def cities = this.restTemplate.getForObject("/cities/list", List)

        then: "the result should  the cites 'Paris' 'Rennes' 'Bordeaux' 'Reims'"
        cities.collect{it.name}.size() == 4
        cities.collect{it.name} == ['Paris', "Rennes", "Bordeaux", "Reims"]

    }


}
