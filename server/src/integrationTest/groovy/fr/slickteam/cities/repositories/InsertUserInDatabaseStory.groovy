package fr.slickteam.cities.repositories

import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import com.github.springtestdbunit.annotation.ExpectedDatabase
import com.github.springtestdbunit.assertion.DatabaseAssertionMode
import fr.slickteam.cities.mappers.IUserMapper
import fr.slickteam.cities.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException

/**
 * Created by jguidoux on 28/05/2017.
 */
@DatabaseSetup("/dbunit/users.xml")
class InsertUserInDatabaseStory extends DbUnitIntegratonTestBase {

    @Autowired
    IUserMapper userMapper

    @ExpectedDatabase(value = "/dbunit/users_with_baghera.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown
    def "insert a non existing user un database"() {

        given: "the database does not contains a user baghera"
        User newUser = new User(null, "baghera", "baghera@slickteam.fr", "123456")
        and: "the given users already in base number"
        def nb = userMapper.countUsers()

        when: "I want to insert halyna in the database"
        userMapper.addNewUser(newUser)

        then: "the user id should not be null"
        newUser.id != null
        and: "the nb of users should be incremented of 1 "
        userMapper.countUsers() == nb + 1


    }

    def "insert a non existing user un database but with an existing id"() {

        given: "the database does not contains a user halyna0"
        and: "halyna id is 1"
        User newUser = new User(1, "halyna", "halyna@slickteam.fr", "123456")


        when: "I want to insert halyna in the database"
        userMapper.addNewUser(newUser)

        then: "an exception should be launch"
        thrown(DuplicateKeyException)


    }

    def "insert a non existing user un database but with an existing login"() {

        given: "the database does not contains a user halyna"
        and: "halyna id is 1"
        User newUser = new User(null, "halyna", "halyna2@slickteam.fr", "123456")


        when: "I want to insert halyna in the database"
        userMapper.addNewUser(newUser)

        then: "an exception should be launch"
        thrown(DuplicateKeyException)


    }


    def "insert a non existing user un database but with an existing email"() {

        given: "the database does not contains a email halyna@slickteam.fr"
        and: "halyna id is 1"
        User newUser = new User(null, "halyna2", "halyna@slickteam.fr", "123456")


        when: "I want to insert halyna in the database"
        userMapper.addNewUser(newUser)

        then: "an error should be launche"
        thrown(DuplicateKeyException)


    }


    @ExpectedDatabase(value = "/dbunit/users_with_baghera.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown
    def "insert a non existing user un database but with an not null id"() {

        given: "the database does not contains a user halyna0"
        and: "halyna id is 1"
        User newUser = new User(28, "baghera", "baghera@slickteam.fr", "123456")


        when: "I want to insert halyna in the database"
        userMapper.addNewUser(newUser)

        then: "the user id should not be 28"
        newUser.id != 28


    }
}
