package fr.slickteam.cities.web

import fr.slickteam.cities.IntegrationTestBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Stepwise

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * Created by jguidoux on 12/05/2017.
 * test if the login works well
 */
@AutoConfigureMockMvc
@Stepwise
class RegiserNewUserStory extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc
    def login = "gaston"
    def mail = "gaston.lagaffe@mail.com"
    def password = "password"

    def "register a new non existing user"() {


        given: "register a new user"

        when: "I try to log with these informations"
        def request = mockMvc.perform(post("/signup")
                .param("login", login)
                .param("email", mail)
                .param("password", password).with(csrf()))


        then: "the request status should be 'Found' (302)"
        request.andExpect(status().isFound())

        and: "the redirected url should be /login"
        request.andExpect(redirectedUrl("/login?signupSuccess"))

    }

    def "connect with this new login"() {


        given: "the user 'gaston' with password 'password"


        when: "I try to register a new user"
        def request = mockMvc.perform(formLogin().user(login).password(password))

        then: "the request status should be 'Found' (302)"
        request.andExpect(status().isFound())

        and: "the redirected url should be /"
        request.andExpect(redirectedUrl("/"))

        and: "The user should be authenticated"
        request.andExpect(authenticated().withUsername(login))

        cleanup:
        mockMvc.perform(post("/logout"))
    }

    def "register a new non existing user with existing login"() {


        given: "register a new user"

        when: "I try to register a new user with existiong login"
        def request = mockMvc.perform(post("/signup")
                .param("login", login)
                .param("email", "unmail@bidon.fr")
                .param("password", "123456").with(csrf()))


        then: "the request status should be 'Ok' (200)"
        request.andExpect(status().isOk())

        and: "the return view name should be /signup"
        request.andExpect(view().name("signup"))

    }


    def "register a new non existing user with existing email"() {


        given: "register a new user"

        when: "I try to register a new user with existiong login"
        def request = mockMvc.perform(post("/signup")
                .param("login", "childerx")
                .param("email", mail)
                .param("password", "123456").with(csrf()))


        then: "the request status should be 'Ok' (200)"
        request.andExpect(status().isOk())

        and: "the return view name should be /signup"
        request.andExpect(view().name("signup"))

    }

    def "register a new non existing user with too short password"() {


        given: "register a new user"

        when: "I try to register a new user with existiong login"
        def request = mockMvc.perform(post("/signup")
                .param("login", "childerx")
                .param("email", "unpetitmail@bla.fr")
                .param("password", "1234").with(csrf()))


        then: "the request status should be 'Ok' (200)"
        request.andExpect(status().isOk())

        and: "the return view name should be /signup"
        request.andExpect(view().name("signup"))

    }

}
