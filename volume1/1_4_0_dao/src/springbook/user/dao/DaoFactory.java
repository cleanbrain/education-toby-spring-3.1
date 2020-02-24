package springbook.user.dao;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2018-12-26
 */
public class DaoFactory {

    public UserDao userDao() {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }

}
