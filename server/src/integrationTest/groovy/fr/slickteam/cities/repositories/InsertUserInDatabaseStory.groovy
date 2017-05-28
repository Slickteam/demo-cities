package fr.slickteam.cities.repositories

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseSetup
import fr.slickteam.cities.mappers.IUserMapper
import fr.slickteam.cities.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.support.DirtiesContextTestExecutionListener
import org.springframework.test.context.transaction.TransactionalTestExecutionListener
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

/**
 * Created by jguidoux on 28/05/2017.
 */
@SpringBootTest
//( properties = [ "spring.profiles.active: test" ])
@Transactional
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class])
@ActiveProfiles(["test"])
@DatabaseSetup("dbunit/users.xml")
class InsertUserInDatabaseStory extends Specification {

    @Autowired
    IUserMapper userMapper

    def "insert a non existing user un database"() {

        given: "the database does not contains a user halyna"
        User newUser = new User(null, "halyna", "halyna@slickteam.fr", "123456")
        and: "the given users already in base number"
        def nb = userMapper.countUsers()

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
