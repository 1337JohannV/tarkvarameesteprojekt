package ee.ttu.tarkvaratehnika.andmekogujad.spring.conf;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"ee.ttu.tarkvaratehnika.andmekogujad.spring.data"})
public class JpaConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factory.setPackagesToScan("ee.ttu.tarkvaratehnika.andmekogujad.spring.data");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(additionalProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        /*
        // H2
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MySQL;MV_STORE=FALSE;MVCC=FALSE");
        dataSource.setUsername("sa");
        dataSource.setPassword("password");
        // H2
        */

        // PostgreSQL
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(
                "jdbc:postgresql://scraperdb.ctkygaceqt6n.us-east-1.rds.amazonaws.com:5432/scraper_db?user=tarkvaratehnika&password=andmekogujad");
        dataSource.setUsername("tarkvaratehnika");
        dataSource.setPassword("andmekogujad");
        // PostgreSQL

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    Properties additionalProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.hbm2ddl.auto", "validate");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "true");

        // PostgreSQL
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        // PostgreSQL


        /*
        // H2
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("spring.h2.console.enabled", "true");
        properties.setProperty("spring.h2.console.path", "/h2console");
        // H2
        */

        // hibernate search
        properties.setProperty("hibernate.search.default.directory_provider", "filesystem");
        properties.setProperty("hibernate.search.default.indexBase", "var/lucene/indexes");
        return properties;
    }
}