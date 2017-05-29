package fr.slickteam.cities

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

/**
 * Created by jguidoux on 28/05/2017.
 */
@SpringBootTest
@ActiveProfiles("test")
abstract class IntegrationTestBase extends Specification {
}
