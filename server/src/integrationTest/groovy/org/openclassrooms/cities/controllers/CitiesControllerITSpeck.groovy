package org.openclassrooms.cities.controllers

import org.openclassrooms.cities.utils.AbstractMvcSpec
import org.springframework.http.HttpStatus
import org.springframework.test.context.TestPropertySource

/**
 * Created by jguidoux on 04/05/2017.
 */
@TestPropertySource("classpath:/env-test.properties")
class CitiesControllerITSpeck extends AbstractMvcSpec{

    def "liste cities"() {
        when:
        def res = get('/cities/list')

        then:
        res.json.collect{it.name} == ["Paris", "Rennes", "Bordeaux", "Reims"]
        res.status == HttpStatus.OK
    }
}
