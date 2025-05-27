package dev.alejandro.tennis_game.player;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import org.testcontainers.containers.MySQLContainer;


@ActiveProfiles("test")
abstract class AbstractIntegrationTest {

    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.4.4")
        .withDatabaseName("tennisgame")
        .withUsername("testuser")
        .withPassword("testpass"); 

    static {
        mysql.start();
    }

    @DynamicPropertySource
    static void mySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> 
        String.format("jdbc:mysql://%s:%d/tennisgame?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                      mysql.getHost(), mysql.getFirstMappedPort()));
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

}
