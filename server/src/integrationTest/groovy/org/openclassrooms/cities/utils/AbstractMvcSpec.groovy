package org.openclassrooms.cities.utils

import com.sun.jndi.toolkit.url.Uri
import org.openclassrooms.cities.Application
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.util.UriComponentsBuilder
import spockmvc.SpockMvcResult
import spockmvc.SpockMvcSpec

/**
 * Created by jguidoux on 04/05/2017.
 */
@ContextConfiguration(
       // loader = SpringApplicationContextLoader,
        classes = [Application]
)
class AbstractMvcSpec extends SpockMvcSpec{

    @Override
    MockMvc buildMockMvc(WebApplicationContext wac) {

        MockMvcBuilders.webAppContextSetup(wac).build()
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
