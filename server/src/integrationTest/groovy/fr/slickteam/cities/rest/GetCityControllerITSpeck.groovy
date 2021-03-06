package fr.slickteam.cities.rest

import fr.slickteam.cities.utils.AbstractMvcSpec
import org.springframework.http.HttpStatus

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
class GetCityControllerITSpeck extends AbstractMvcSpec{


    def "try access url '/api/rest/cities/get which not exist"() {


        when: "I ask for the rest api '/api/rest/citiess/get?name=Rennes'"
        def res = get('/api/rest/citiess/get', [ name: "Rennes"])



        then: "The response status should be Forbidden'"
        res.status == HttpStatus.NOT_FOUND


    }

    def "ask for a city  which does not exist from rest api '/api/rest/cities/get with json result"() {

        given: "this city : 'Guérande' does not exist in the repository"

        when: "I ask for the rest api '/api/rest/cities/get?name=Guérande'"
        def res = get('/api/rest/cities/get', [ name: "Guérande"])



        then: "the response request should have status 'not found'"
        res.status == HttpStatus.INTERNAL_SERVER_ERROR


    }


    def "ask for a city from rest api '/api/rest/cities/get with json result"() {

        given: "this city : 'Rennes' does not exist in the repository"

        when: "I ask for the rest api '/cities/get?name=Rennes'"
        def res = get('/api/rest/cities/get', [ name: "Rennes"])




        then: "the return city should have for name 'Rennes'"
        res.status == HttpStatus.OK
        res.json.name == "Rennes"


    }
}
