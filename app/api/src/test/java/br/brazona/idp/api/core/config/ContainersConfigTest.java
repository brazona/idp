package br.brazona.idp.api.core.config;

import br.brazona.idp.api.IdpApplication;
import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

@SpringBootTest(classes = IdpApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class ContainersConfigTest{


    @LocalServerPort
    private Integer port;
    private static final Integer PORT_DB = 5454;

    static Supplier<String>  LIQUIBASE_CHANGELOG = () -> "classpath:db/changelog-master.yml";
    static Supplier<Boolean>  LIQUIBASE_ENABLED = () -> true;
    static Supplier<String>  LIQUIBASE_SQL_USER_NAME_1 = () -> "user1";


    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
                "postgres:12-alpine"
    );

    static KeycloakContainer keycloakContainer = new KeycloakContainer
            ("keycloak/keycloak:latest")
//            .withRealmImportFile("./src/main/resources/realm-export.json")
            .withStartupTimeout(Duration.of(15, ChronoUnit.MINUTES))
//            .withCommand("start-dev")
            .withAdminPassword("admin")
            .withAdminUsername("admin");

    static {
        postgres
                .withDatabaseName("idp")
                .withUsername("idp_dev")
                .withPassword("123456")
                .withInitScript("db/script/init.sql")
                .start();

        keycloakContainer
                .dependsOn(postgres)
                .withRealmImportFile("realm-brazona.json")
                .start();
    }
    @BeforeAll
    static void beforeAll() {
        keycloakContainer.addEnv("KC_DB",postgres.getJdbcUrl());
        keycloakContainer.addEnv("KC_DB_URL",postgres.getJdbcUrl());
        keycloakContainer.addEnv("KC_DB_USERNAME",postgres.getJdbcUrl());
        keycloakContainer.addEnv("KC_DB_PASSWORD",postgres.getJdbcUrl());
        keycloakContainer.addEnv("KEYCLOAK_ADMIN",postgres.getJdbcUrl());
        keycloakContainer.addEnv("KEYCLOAK_ADMIN_PASSWORD",postgres.getJdbcUrl());
    }

    @AfterAll
    static void afterAll() {
        keycloakContainer.stop();
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {

        Supplier<String>  KEYCLOAK_URL = () -> "http://localhost:"+keycloakContainer.getHttpPort()+"/realms/brazona";

        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);

        registry.add("spring.liquibase.url", postgres::getJdbcUrl);
        registry.add("spring.liquibase.password", postgres::getPassword);
        registry.add("spring.liquibase.user", postgres::getUsername);
        registry.add("spring.liquibase.change-log", LIQUIBASE_CHANGELOG::get);
        registry.add("spring.liquibase.enabled", LIQUIBASE_ENABLED::get);
        registry.add("spring.liquibase.parameters.sql_user_name_1", LIQUIBASE_SQL_USER_NAME_1::get);

        registry.add("keycloak.url", KEYCLOAK_URL::get);
    }

    @Test
    @Order(1)
    @DisplayName("Should Container Create Postgres")
    void should_container_create_postgres() {
        Assertions.assertTrue(postgres.isCreated());
    }
    @Test
    @Order(2)
    @DisplayName("Should Container Run Postgres")
    void should_container_run_postgres() {
        Assertions.assertTrue(postgres.isRunning());
    }
    @Test
    @Order(3)
    @DisplayName("Should Container Create Keycloak")
    void should_container_create_keycloak() {
        Assertions.assertTrue(keycloakContainer.isCreated());
    }
    @Test
    @Order(4)
    @DisplayName("Should Container Run Keycloak")
    void should_container_run_keycloak() {
        Assertions.assertTrue(keycloakContainer.isRunning());
    }
}
