package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2018-12-26
 */
@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

//        dataSource.setDriverClass(oracle.jdbc.driver.OracleDriver.class);
//        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
//        dataSource.setUsername("scott");
//        dataSource.setPassword("tiger");

        return dataSource;
    }
}
