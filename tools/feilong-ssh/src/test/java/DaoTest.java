

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath*:loxia-hibernate-context.xml",//
									"classpath*:loxia-service-context.xml",//
									"classpath*:spring/spring-dao.xml"//
})
public class DaoTest extends AbstractJUnit4SpringContextTests{

	@Autowired
	private LocalSessionFactoryBean	sessionFactory;

	@Test
	public void createTables(){
		sessionFactory.updateDatabaseSchema();
	}
}
