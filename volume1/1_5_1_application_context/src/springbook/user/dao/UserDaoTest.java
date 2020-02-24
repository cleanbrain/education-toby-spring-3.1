package springbook.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.domain.User;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2018-12-17
 */
public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        /*
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("cleanbrain");
        user.setName("노상현");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        */

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao1 = context.getBean("userDao", UserDao.class);
        UserDao dao2 = context.getBean("userDao", UserDao.class);

        System.out.println(dao1);
        System.out.println(dao2);

        if (dao1 == dao2)
            System.out.println("dao1, dao2 equals!");

        /*
        DaoFactory factory = new DaoFactory();

        UserDao dao1 = factory.userDao();
        UserDao dao2 = factory.userDao();

        System.out.println(dao1);
        System.out.println(dao2);
        */
    }
}
