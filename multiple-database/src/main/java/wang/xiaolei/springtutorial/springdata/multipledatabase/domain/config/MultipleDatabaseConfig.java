package wang.xiaolei.springtutorial.springdata.multipledatabase.domain.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Create by wangxiaolei on 2018/5/25 6:42 PM
 */
@Configuration
//@PropertySource({ "classpath:application.properties" })

public class MultipleDatabaseConfig {


    @Configuration
    @EnableJpaRepositories(basePackages = "wang.xiaolei.springtutorial.springdata.multipledatabase.domain.repository.first",entityManagerFactoryRef = "customerEntityManagerFactory",
            transactionManagerRef = "customerTransactionManager")
    public class Config1 {

        @Bean
        @Primary
        PlatformTransactionManager customerTransactionManager() {
            return new JpaTransactionManager(customerEntityManagerFactory().getObject());
        }

        @Bean
        @Primary
        LocalContainerEntityManagerFactoryBean customerEntityManagerFactory() {

            HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
            jpaVendorAdapter.setGenerateDdl(true);

            LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

            factoryBean.setDataSource(firstDataSource());
            factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
            factoryBean.setPackagesToScan(new String[] { "wang.xiaolei.springtutorial.springdata.multipledatabase.domain.model.entity.first" });

            return factoryBean;
        }

        @Bean
        @Primary
        @ConfigurationProperties("app.datasource.first")
        public DataSourceProperties firstDataSourceProperties() {
            return new DataSourceProperties();
        }

        @Bean(name = "firstDataSource")
        @Primary
        @ConfigurationProperties("app.datasource.first")
        public DataSource firstDataSource() {
            return firstDataSourceProperties().initializeDataSourceBuilder().build();
        }
    }




    @Configuration
    @EnableJpaRepositories(basePackages = "wang.xiaolei.springtutorial.springdata.multipledatabase.domain.repository.second", entityManagerFactoryRef = "secondEntityManagerFactory",
            transactionManagerRef = "secondTransactionManager")
    public class Config2 {

        @Bean
        PlatformTransactionManager secondTransactionManager() {
            return new JpaTransactionManager(secondEntityManagerFactory().getObject());
        }

        @Bean
        LocalContainerEntityManagerFactoryBean secondEntityManagerFactory() {

            HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
            jpaVendorAdapter.setGenerateDdl(true);

            LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

            factoryBean.setDataSource(secondDataSource());
            factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
            factoryBean.setPackagesToScan(new String[] { "wang.xiaolei.springtutorial.springdata.multipledatabase.domain.model.entity.second" });
//        factoryBean.setPackagesToScan(SecondConfig.class.getPackage().getName());

            return factoryBean;
        }

        @Bean
        @ConfigurationProperties("app.datasource.second")
        public DataSourceProperties secondDataSourceProperties() {
            return new DataSourceProperties();
        }

        @Bean(name = "secondDataSource")
        @ConfigurationProperties("app.datasource.second")
        public DataSource secondDataSource() {
            return secondDataSourceProperties().initializeDataSourceBuilder().build();
        }
    }


}
