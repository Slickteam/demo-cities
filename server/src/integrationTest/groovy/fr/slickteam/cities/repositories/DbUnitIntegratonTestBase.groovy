package fr.slickteam.cities.repositories

import com.github.springtestdbunit.DbUnitTestExecutionListener
import fr.slickteam.cities.IntegrationTestBase
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.support.DirtiesContextTestExecutionListener
import org.springframework.test.context.transaction.TransactionalTestExecutionListener
import org.springframework.transaction.annotation.Transactional

/**
 * Created by jguidoux on 28/05/2017.
 */
@Transactional
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class])
class DbUnitIntegratonTestBase extends IntegrationTestBase {
}
