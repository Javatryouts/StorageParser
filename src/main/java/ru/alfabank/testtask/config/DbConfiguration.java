package ru.alfabank.testtask.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@AllArgsConstructor
@Configuration
class DbConfiguration {
    private final DbConfigurationData configurationData;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("ru.alfabank.testtask.model");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        Properties additionalProperties = new Properties();
        additionalProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        additionalProperties.put("hibernate.hbm2ddl.auto", "none");
        entityManagerFactory.setJpaProperties(additionalProperties);
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }


    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws IllegalArgumentException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(configurationData.getJdbcDriver());
        hikariConfig.setJdbcUrl(configurationData.getJdbcUrl());
        hikariConfig.setUsername(configurationData.getJdbcUser());
        hikariConfig.setPassword(configurationData.getJdbcPassword());
        hikariConfig.setMaximumPoolSize(configurationData.getPoolSize());
        return new HikariDataSource(hikariConfig);
    }
}
