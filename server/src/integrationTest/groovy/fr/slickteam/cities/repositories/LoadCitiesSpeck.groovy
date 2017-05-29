package fr.slickteam.cities.repositories

import fr.slickteam.cities.service.ICityService
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by jguidoux on 22/04/2017.
 *
 * unit test to test get list cities from the repository
 */
class LoadCitiesSpeck extends DbUnitIntegratonTestBase {

    @Autowired
    ICityService repo



    def "list all  cities"() {

        given: "a file containing the cities of 'Paris' 'Rennes' 'Bordeaux' 'Reims'"


        when: 'I ask for the list of all the cities'
        def cities = repo.listCities()

        then: "the result should be 'Paris' 'Rennes' 'Bordeaux' 'Reims'"
        cities.collect { it.name }.size() == 4

    }
}
