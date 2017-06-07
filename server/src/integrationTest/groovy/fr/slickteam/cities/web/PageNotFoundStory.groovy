package fr.slickteam.cities.web

import fr.slickteam.cities.IntegrationTestBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by jguidoux on 11/05/2017.
 * test the security access
 */
@AutoConfigureMockMvc
class PageNotFoundStory extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc


    @WithMockUser
    def "test get page which does not exist"() {

        given: "I'm connected"

        when: "I try to acces the url /not_exist"
        def request = this.mockMvc.perform(get("/not_exist"))

        then: "The user should  be authenticated"
        request.andExpect(status().isNotFound())


    }


}
