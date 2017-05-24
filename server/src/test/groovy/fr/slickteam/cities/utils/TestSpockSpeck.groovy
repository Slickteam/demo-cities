package fr.slickteam.cities.utils

import fr.slickteam.cities.repositories.ICitiesRepository
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by jguidoux on 02/05/2017.
 * This files juste contains some samples for spock
 */
class TestSpockSpeck extends Specification {

    @Unroll
    def "length of Spock's and his friends' names"() {
        expect:
        name.size() == length

        where:
        name     | length
        "Spock"  | 5
        "Kirk"   | 4
        "Scotty" | 6
    }



    def "test mock"() {
        given:
        Interface1 mock = Mock() {
            m1() >> ["a", "a"]
        }

        when:
        def result = mock.m1()

        then:
        result == ["a", "a"]
    }


    def "test mock1"() {
        given:
        Interface1 mock = Mock() {
           1*  m1() >> ["a", "a"]
           1*  m1() >> ["r", "r"]
        }

        Class1 maClass = new Class1()
        maClass.monInterface = mock

        when:
        def result = maClass.m2()
        def result2 = maClass.m2()

        then:
        result == ["a", "a", "b"]
        result2 == ["r", "r", "b"]
    }


    def "test mock2"() {

        given:
        ICitiesRepository repo = Mock() {
            listCities() >> []
        }

        when:
        def cities = repo.listCities()

        then:
//        1 * repo.listCities()
        cities.size() == 0
    }

}



interface Interface1 {
    List<String> m1()
    void m2()
}

class Class1 {

    Interface1 monInterface

    List<String> m2() {
        monInterface.m2()
        return monInterface.m1() << "b"
    }
}

