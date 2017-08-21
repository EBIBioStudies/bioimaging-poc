package uk.ac.ebi.biostd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uk.ac.ebi.biostd.persistence.common.custom.CustomRepositoryImpl;

/**
 * Main spring boot application page.
 */
@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class RestApiApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RestApiApplication.class, args);
    }
}
