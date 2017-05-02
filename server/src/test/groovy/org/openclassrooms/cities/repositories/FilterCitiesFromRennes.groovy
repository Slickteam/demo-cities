package org.openclassrooms.cities.repositories

import org.openclassrooms.cities.repositories.impl.CitiesRepository
import spock.lang.Specification

/**
 * Created by jguidoux on 03/05/2017.
 */
class FilterCitiesFromRennes extends Specification{


    def "je recherche des villes qui ne sont pas contenu dans la liste des villes" () {
        given:"un repository se basant sur un fichier existant"
        def filePath = 'villes-test.txt'
        CitiesRepository repo = new CitiesRepository(filePath)
        repo.setup()

        when: "je demande les villes commençant par gué"
        def cities = repo.filterCities("gué")

        then:
        cities.isEmpty()

    }

    def "je recherche des villes qui sont contenu dans la liste des villes" () {
        given:"un repository se basant sur un fichier existant"
        def filePath = 'villes-test.txt'
        CitiesRepository repo = new CitiesRepository(filePath)
        repo.setup()

        when: "je demande les villes commençant par re"
        def cities = repo.filterCities("re")

        then:
        cities.collect{it.name} == [ "Rennes", "Reims"]

    }
}
