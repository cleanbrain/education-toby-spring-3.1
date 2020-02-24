package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2018-12-17
 */
public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        UserDao dao = new DaoFactory().userDao();

        User user = new User();
        user.setId("cleanbrain");
        user.setName("노상현");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
    }
}
