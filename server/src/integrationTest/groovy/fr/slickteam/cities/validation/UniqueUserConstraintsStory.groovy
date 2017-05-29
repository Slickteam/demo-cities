package fr.slickteam.cities.validation

import fr.slickteam.cities.dto.Account
import fr.slickteam.cities.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import javax.validation.ConstraintViolation
import javax.validation.Validator

/**
 * Created by jguidoux on 23/05/2017.
 */
@SpringBootTest
@AutoConfigureMockMvc
class UniqueUserConstraintsStory extends Specification {


    @Autowired
    private IUserService userService
    @Autowired
    private Validator validator


    def "I can't add a user with an existing login"() {
        given: "The contain a user with login 'robert"
        String login = "robert"
        Account predefinedUser = new Account(login, "robert.martin@gmail.com", "pass-word")
        userService.registerNewUserAccount(predefinedUser)

        when: "I try to add a user with same login"
        Account newUser = new Account(login, "robert.d.junior@gmail.com", "123456")
        Set<ConstraintViolation<Account>> violations = validator.validate(newUser)

        then: "it should be 1 constraint violations"
        violations.size() == 1

        cleanup: "delete the user"
        userService.deleteUser(predefinedUser)

    }

    def "I can't add a user with an existing email"() {
        given: "The contain a user with login 'robert.martin@gmail.com"
        String email = "robert.martin@gmail.com"
        Account predefinedUser = new Account("robert", email, "pass-word")
        userService.registerNewUserAccount(predefinedUser)

        when: "I try to add a user with same email"
        Account newUser = new Account("roby", email, "123456")
        Set<ConstraintViolation<Account>> violations = validator.validate(newUser)

        then: "it should be 1 constraint violations"
        violations.size() == 1

        cleanup: "delete the user"
        userService.deleteUser(predefinedUser)

    }


    def "I can add a user "() {
        given: "The contain no added user"


        when: "I try to add a user with same email"
        Account newUser = new Account("roby", "robert.c.martin@gmail.com", "123456")
        Set<ConstraintViolation<Account>> violations = validator.validate(newUser)

        then: "it should be 0 constraint violations"
        violations.size() == 0

    }
}
