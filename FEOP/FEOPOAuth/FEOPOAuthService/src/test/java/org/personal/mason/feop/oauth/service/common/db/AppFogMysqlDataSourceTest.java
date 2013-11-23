package org.personal.mason.feop.oauth.service.common.db;

import org.junit.Test;
import org.personal.mason.feop.oauth.common.cf.service.AppFogMysqlDataSource;

public class AppFogMysqlDataSourceTest {

@Test
public void testSetServiceName() {
	AppFogMysqlDataSource dataSource = new AppFogMysqlDataSource();
	dataSource.setServiceName("foep-db-contact");
	System.out.println(true);
}

}
