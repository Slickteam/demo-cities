package fr.slickteam.cities.repositories

import fr.slickteam.cities.repositories.impl.CitiesRepository
import spock.lang.Specification

/**
 * Created by jguidoux on 22/04/2017.
 *
 * unit test to test get list cities from the repository
 */
class LoadCitiesSpeck extends Specification {


    def "the specify file wich should contain the cities does not exist"() {

        given: "the file which contain all cities does not exist"
        def filePath = 'villes-faux.txt'
        CitiesRepository repo = new CitiesRepository(filePath)

        when: "data base initialisation"
        repo.setup()

        then: "a file not found exception should be launch"
        def ex = thrown(FileNotFoundException)
        ex.message == "class path resource [villes-faux.txt] cannot be opened because it does not exist"

    }

    def "list all  cities"() {

        given: "a file containing the cities of 'Paris' 'Rennes' 'Bordeaux' 'Reims'"
        def filePath = 'villes-test.txt'
        CitiesRepository repo = new CitiesRepository(filePath)
        repo.setup()

        when: 'I ask for the list of all the cities'
        def cities = repo.listCities()

        then: "the result should be 'Paris' 'Rennes' 'Bordeaux' 'Reims'"
        cities.collect { it.name }.size() == 4

    }
}
