package springbook.user.service;

import springbook.user.domain.User;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-02-18
 */
public interface UserService {
    void add(User user);
    void upgradeLevels() throws SQLException;
}
