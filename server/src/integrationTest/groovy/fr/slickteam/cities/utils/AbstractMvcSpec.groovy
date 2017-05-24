package fr.slickteam.cities.utils

import fr.slickteam.cities.Application
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.util.UriComponentsBuilder
import spockmvc.SpockMvcResult
import spockmvc.SpockMvcSpec

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * Created by jguidoux on 04/05/2017.
 */
@ContextConfiguration(
       // loader = SpringApplicationContextLoader,
        classes = [Application]
)
@WebAppConfiguration
class AbstractMvcSpec extends SpockMvcSpec{

    @Override
    MockMvc buildMockMvc(WebApplicationContext wac) {

        MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity())
                .build()
    }

    SpockMvcResult get(URI uri) {

        return get(uri.toString())
    }

    SpockMvcResult get(URI uri, Map<String, String> params) {

        def builder = UriComponentsBuilder.fromUri(uri)
        params.each { key, value ->
                builder.queryParam(key, value).build().toUri()

        }
        return get(builder.build().toUri())
    }

    SpockMvcResult get(String url, Map<String, String> params) {

        def builder = UriComponentsBuilder.fromUriString(url)
        params.each { key, value ->
            builder.queryParam(key, value).build().toUri()

        }
        return get(builder.build().toUri())
    }
}
