package org.openclassrooms.cities.utils

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

}
