package fr.slickteam.cities.repositories

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseSetup
import fr.slickteam.cities.mappers.IUserMapper
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
@Transactional
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class])
@ActiveProfiles("test")
class CountUsersInDatabaseStory extends Specification {

    @Autowired
    IUserMapper userMapper

    @DatabaseSetup("/dbunit/users.xml")
    def "count nb users in database"() {

        given: "the database contains 1 user"


        when: "I want to count the number of user"
        def nb = userMapper.countUsers()

        then: "the numbers of users should be 1"
        nb == 1


    }


}
