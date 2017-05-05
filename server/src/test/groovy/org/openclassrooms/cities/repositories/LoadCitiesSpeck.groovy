package org.openclassrooms.cities.repositories

import org.assertj.core.util.Lists
import org.openclassrooms.cities.repositories.impl.CitiesRepository
import spock.lang.Specification


/**
 * Created by jguidoux on 22/04/2017.
 */
class LoadCitiesSpeck extends Specification {


    def "le fichier spécifier est faux"() {
        given: "un repository se basant sur un fichier inexistant"
        def filePath = 'villes-faux.txt'
        CitiesRepository repo = new CitiesRepository(filePath)

        when: "j'initialise la base"
        repo.setup()
        repo.listCities()

        then:
        def ex = thrown(FileNotFoundException)
        ex.message == "file '$filePath' does not exist!"

    }

    def "list cities"() {
        given: 'mon fichier villes-test.txt contenant Paris et Rennes'
        def filePath = 'villes-test.txt'
        CitiesRepository repo = new CitiesRepository(filePath)
        repo.setup()

        when: 'je demande la liset des villes'
        def cities = repo.listCities()

        then: 'la liste doit contenir Paris et Rennes'
        cities.collect { it.name }.size() == 4

    }
}
