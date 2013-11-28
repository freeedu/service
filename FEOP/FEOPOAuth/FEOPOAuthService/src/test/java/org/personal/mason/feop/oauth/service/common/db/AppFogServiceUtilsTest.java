package org.personal.mason.feop.oauth.service.common.db;

import org.codehaus.jackson.map.ObjectMapper;
import org.personal.mason.feop.oauth.common.cf.service.AppFogServicesConfiguration;

import java.io.IOException;


public class AppFogServiceUtilsTest {

    public static void main(String[] args) throws IOException {
        String jsonString = "{\"mysql-5.1\":[{\"name\":\"foep-db-contact\",\"label\":\"mysql-5.1\",\"plan\":\"free\",\"tags\":[\"mysql\",\"mysql-5.1\",\"relational\",\"mysql-5.1\",\"mysql\"],\"credentials\":{\"name\":\"d5bd6568be59b4d31a4cad18ac78af189\",\"hostname\":\"10.0.61.235\",\"host\":\"10.0.61.235\",\"port\":3306,\"user\":\"ueF5F0sZnhj2g\",\"username\":\"ueF5F0sZnhj2g\",\"password\":\"ppS2nhGjRimkt\"}}]}";
//		String jsonString = "{\"mysql-5.1\":[{\"name\":\"foep-db-contact\",\"label\":\"mysql-5.1\",\"plan\":\"free\",\"tags\":[\"mysql\",\"mysql-5.1\",\"relational\",\"mysql-5.1\",\"mysql\"],\"credentials\":{\"name\":\"d5bd6568be59b4d31a4cad18ac78af189\",\"hostname\":\"10.0.61.235\",\"host\":\"10.0.61.235\",\"port\":3306,\"user\":\"ueF5F0sZnhj2g\",\"username\":\"ueF5F0sZnhj2g\",\"password\":\"ppS2nhGjRimkt\"}}]}";
        ObjectMapper mapper = new ObjectMapper();
        AppFogServicesConfiguration config = mapper.readValue(jsonString, AppFogServicesConfiguration.class);
        System.out.println(config.getMysql().size());

    }

}
