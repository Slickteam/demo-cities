package fr.slickteam.cities.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.TestPropertySource
import org.springframework.web.util.UriComponentsBuilder
import spock.lang.Specification

/**
 * Created by jguidoux on 02/05/2017.
 * test list cities from the Rest API
 *
 * integration test to test the rest api /cities/filtes
 * This api should get the complete list of cities contains in the servert
 *
 * this test show the use of the TestRestTemplate provided by Spring-boot
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:/env-test.properties")
class FilterCitiesTestRestTempleSpeck extends Specification {


    @Autowired
    private TestRestTemplate restTemplate

    def "ask for cities which are not in database"() {
        given: "this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"

        when: "I ask for cities which name start with 'gué"
        def cities = this.restTemplate.getForObject("/api/rest/cities/filter?startWith=gué", List)

        then: "the result should be empty"
        cities.collect { it.name }.size() == 0
    }

    def "ask for cities which are  in database"() {
        given: "this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"

        when: "I ask for cities which name start with 're"
        URI url = UriComponentsBuilder.fromUriString("/api/rest/cities/filter").
                queryParam("startWith", "re").build().toUri()
        def cities = this.restTemplate.getForObject(url, List)

        then: "the result should  the cites 'Paris' 'Rennes' 'Bordeaux' 'Reims'"

        cities.collect { it.name }.size() == 2
        cities.collect { it.name } == ["Rennes", "Reims"]

    }
}
