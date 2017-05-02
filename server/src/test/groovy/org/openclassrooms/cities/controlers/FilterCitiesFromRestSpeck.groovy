package org.openclassrooms.cities.controlers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

/**
 * Created by jguidoux on 03/05/2017.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:/env-test.properties")
class FilterCitiesFromRestSpeck extends Specification{


    @Autowired
    private TestRestTemplate restTemplate

    def "ask for cities which are not in database"(){
        given:
        when:
        def cities = this.restTemplate.getForObject("/cities/filter?startWith=gué", List)

        then:
        cities.collect{it.name}.size() == 0
    }

    def "ask for cities which are  in database"(){
        given:
        when:
        def cities = this.restTemplate.getForObject("/cities/filter?startWith=re", List)

        then:
        cities.collect{it.name}.size() == 2
    }
}
