package org.openclassrooms.cities.controllers

import org.openclassrooms.cities.utils.AbstractMvcSpec
import org.springframework.http.HttpStatus
import org.springframework.test.context.TestPropertySource
import spockmvc.RequestParams

/**
 * Created by jguidoux on 02/05/2017.
 * test list cities from the Rest API
 *
 * integration test to test the rest api /cities/filtes
 * This api should get the complete list of cities contains in the servert
 *
 * this test show the use the spring-mvc way to test a rest api
 * this test also use the spock-spring-mvc library to help to write test
 * see https://github.com/geowarin/spring-spock-mvc
 * this library contains the groovy json libray which is a nice library to manipulate json
 * this is my prefer way to test rese api
 */
@TestPropertySource("classpath:/env-test.properties")
class FilterCitiesITSpeck extends AbstractMvcSpec {




    def "ask for cities which are not in database"() {

        given: "this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"

        when: "I ask for cities which name start with 'gué"
        def res = get('/api/rest/cities/filter', [ startWith: "gué"])
        def cities = res.json

        then: "the result should be empty"
        res.status == HttpStatus.OK
        cities.collect { it.name }.size() == 0
    }

    def "ask for cities which are  in database"() {

        given: "this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"

        when: "I ask for cities which name start with 're"
        def res = get('/api/rest/cities/filter',  [ startWith: "re"])
        def cities = res.json

        then: "the result should  the cites 'Paris' 'Rennes' 'Bordeaux' 'Reims'"

        cities.collect { it.name }.size() == 2
        cities.collect { it.name } == ["Rennes", "Reims"]

    }
}
