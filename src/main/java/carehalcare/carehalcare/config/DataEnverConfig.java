package carehalcare.carehalcare.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@RequiredArgsConstructor
@Configuration
public class DataEnverConfig {

    private final EntityManagerFactory entityManagerFactory;

    @Bean
    AuditReader auditReader(){
        return AuditReaderFactory.get(entityManagerFactory.createEntityManager());
    }
}
