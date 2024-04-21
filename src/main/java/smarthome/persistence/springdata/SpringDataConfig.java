package smarthome.persistence.springdata;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "smarthome.persistence.springdata")
@ComponentScan("smarthome.persistence.springdata")
public class SpringDataConfig {

    LocalContainerEntityManagerFactoryBean em;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        em.setPersistenceUnitName("smartHome");
        em.setPackagesToScan("smarthome.persistence.jpa.datamodel");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
