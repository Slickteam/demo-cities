package org.openclassrooms.cities.validation

import org.openclassrooms.cities.model.User
import org.openclassrooms.cities.repositories.impl.UserRepository
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
class RegisterNewUserStory extends Specification {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Validator validator;


    def "I can't add a user with an existing login"() {
        given: "The contain a user with login 'robert"
        String login = "robert";
        User predefinedUser = new User(login, "robert.martin@gmail.com", "pass-word")
        userRepository.addNewUser(predefinedUser)

        when: "I try to add a user with same login"
        User newUser = new User(login, "robert.d.junior@gmail.com", "123456")
        Set<ConstraintViolation<User>> violations = validator.validate(newUser)

        then: "it should be 1 constraint violations"
        violations.size() == 1

    }

    def "I can't add a user with an existing email"() {
        given: "The contain a user with login 'robert.martin@gmail.com"
        String email = "robert.martin@gmail.com";
        User predefinedUser = new User("robert", email, "pass-word")
        userRepository.addNewUser(predefinedUser)

        when: "I try to add a user with same email"
        User newUser = new User("roby", email, "123456")
        Set<ConstraintViolation<User>> violations = validator.validate(newUser)

        then: "it should be 1 constraint violations"
        violations.size() == 1

    }


    def "I can't add a user "() {
        given: "The contain no added user"


        when: "I try to add a user with same email"
        User newUser = new User("roby", "robert.c.martin@gmail.com", "123456")
        Set<ConstraintViolation<User>> violations = validator.validate(newUser)

        then: "it should be 1 constraint violations"
        violations.size() == 0

    }
}
