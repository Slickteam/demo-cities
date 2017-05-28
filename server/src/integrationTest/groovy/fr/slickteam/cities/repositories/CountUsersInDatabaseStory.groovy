package fr.slickteam.cities.repositories

import com.github.springtestdbunit.annotation.DatabaseSetup
import fr.slickteam.cities.mappers.IUserMapper
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by jguidoux on 28/05/2017.
 */
class CountUsersInDatabaseStory extends DbUnitIntegratonTestBase {

    @Autowired
    IUserMapper userMapper

    @DatabaseSetup("/dbunit/users.xml")
    def "count nb users in database"() {

        given: "the database contains 3 user"


        when: "I want to count the number of user"
        def nb = userMapper.countUsers()

        then: "the numbers of users should be 1"
        nb == 3


    }


}
