package org.openclassrooms.cities.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jguidoux on 12/05/2017.
 * test if the login works well
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:/env-test.properties")
@WithMockUser
class LoginPageSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    def "write valid login and password in login page"() {

        given: "the user 'user' with password 'password"
        def user = "user"
        def password = "password"

        when: "I try to log with these informations"
        def request = mockMvc.perform(formLogin().user(user).password(password))

        then: "the request status should be 'Found' (302)"
        request.andExpect(status().isFound())

        and: "the redirected url should be /"
        request.andExpect(redirectedUrl("/"))

        and: "The user should be authenticated"
        request.andExpect(authenticated().withUsername(user))
    }


    def "write invalid login and password in login page"() {

        given: "the user 'user' with password 'invalid"
        def user = "user"
        def password = "invalid"

        when: "I try to log with these informations"
        def request = mockMvc.perform(formLogin().user(user).password(password))

        then: "the request status should be 'Found' (302)"
        request.andExpect(status().isFound())

        and: "the redirected url should be /login-error"
        request.andExpect(redirectedUrl("/login?error"))

        and: "The user should not be authenticated"
        request.andExpect(unauthenticated())
    }


}
