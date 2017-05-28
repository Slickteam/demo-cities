package fr.slickteam.cities.repositories

import fr.slickteam.cities.mappers.IUserMapper
import fr.slickteam.cities.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 * Created by jguidoux on 28/05/2017.
 */
@SpringBootTest
class InsertUserInDatabaseStory extends Specification {

    @Autowired
    IUserMapper userMapper

    def "insert a non existing user un database"() {

        given: "the database does not contains a user halyna"
        User newUser = new User(null, "halyna", "halyna@slickteam.fr", "123456")


        when: "I want to insert halyna in the database"
        userMapper.addNewUser(newUser)

        then: "the user id should not be null"
        newUser.id != null


    }

    def "insert a non existing user un database but with an id"() {

        given: "the database does not contains a user halyna0"
        and: "halyna id is 1"
        User newUser = new User(1, "halyna", "halyna@slickteam.fr", "123456")


        when: "I want to insert halyna in the database"
        userMapper.addNewUser(newUser)

        then: "the user id should not be null"
        newUser.id != null


    }
}
