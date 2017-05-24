package fr.slickteam.cities.rest

import fr.slickteam.cities.utils.AbstractMvcSpec
import org.springframework.http.HttpStatus
import org.springframework.test.context.TestPropertySource

/**
 * Created by jguidoux on 02/05/2017.
 * test list cities from the Rest API
 *
 * integration test to test the rest api /cities/list
 * This api should get the complete list of cities contains in the servert
 *
 * this test show the use the spring-mvc way to test a rest api
 * this test also use the spock-spring-mvc library to help to write test
 * * see https://github.com/geowarin/spring-spock-mvc
 * this library contains the groovy json libray which is a nice library to manipulate json
 * this is my prefer way to test rest api
 */
@TestPropertySource("classpath:/env-test.properties")
class CitiesControllerITSpeck extends AbstractMvcSpec{

    def "list cities from rest api '/api/rest/citie/list with json result"() {

        given:"this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims' contains in the repository"

        when: "I ask for the rest api '/api/rest/cities/list'"
        def res = get('/api/rest/cities/list')

        then: "the result should  the cites 'Paris' 'Rennes' 'Bordeaux' 'Reims'"
        res.status == HttpStatus.OK
        res.json.collect{it.name} == ["Paris", "Rennes", "Bordeaux", "Reims"]
    }
}
