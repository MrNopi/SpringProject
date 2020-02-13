package spring.config;

import javax.sql.DataSource;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import spring.model.User;

@Configuration
@ComponentScan(basePackages = {"spring.dao", "spring.service"})
public class AppConfig {
    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/cinema?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("12345");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        Properties props = new Properties();
        props.put("dialect", "org.hibernate.dialect.MySQL5Dialect");
        props.put("show_sql", "true");
        props.put("hbm2ddl.auto", "create-drop");
        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(User.class);
        return factoryBean;
    }
}
