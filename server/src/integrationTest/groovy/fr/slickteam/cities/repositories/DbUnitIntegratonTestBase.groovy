package fr.slickteam.cities.repositories

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseTearDown
import com.github.springtestdbunit.annotation.DbUnitConfiguration
import com.github.springtestdbunit.dataset.FlatXmlDataSetLoader
import fr.slickteam.cities.IntegrationTestBase
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.support.DirtiesContextTestExecutionListener
import org.springframework.test.context.transaction.TransactionalTestExecutionListener
import org.springframework.transaction.annotation.Transactional

/**
 * Created by jguidoux on 28/05/2017.
 */
@Transactional
@DbUnitConfiguration(dataSetLoader = FlatXmlDataSetLoader.class)
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class])
@DatabaseTearDown
@TestPropertySource("classpath:/env-test.properties")
class DbUnitIntegratonTestBase extends IntegrationTestBase {

//    static final COLUMN_FILTER_ID = DefaultColumnFilter.excludeColumn("id*")
}
