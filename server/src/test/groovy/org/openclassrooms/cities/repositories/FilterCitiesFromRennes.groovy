package org.openclassrooms.cities.repositories

import org.openclassrooms.cities.repositories.impl.CitiesRepository
import spock.lang.Specification

/**
 * Created by jguidoux on 03/05/2017.
 *
 * unit test to test get the filtering of cities which start by a some letters
 */
class FilterCitiesFromRennes extends Specification{


    def "database does not contains cities which start by 'gué'" () {

        given:"this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims'"
        def filePath = 'villes-test.txt'
        CitiesRepository repo = new CitiesRepository(filePath)
        repo.setup()

        when: "I ask cities starting by 'gué'"
        def cities = repo.filterCities("gué")

        then: "the result list should be empty"
        cities.isEmpty()

    }

    def "database contains two cities starting by 're'" () {

        given:"this cities : 'Paris' 'Rennes' 'Bordeaux' 'Reims'"
        def filePath = 'villes-test.txt'
        CitiesRepository repo = new CitiesRepository(filePath)
        repo.setup()

        when: "I ask cities starting by  're'"
        def cities = repo.filterCities("re")

        then:"the result list should contains 'Rennes' and 'Reims'"
        cities.collect{it.name} == [ "Rennes", "Reims"]

    }
}
