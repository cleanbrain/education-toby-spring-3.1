package springbook.user.dao;

import springbook.user.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-01-17
 */
public interface UserDao {
    void add(User user);
    User get(String id);
    List<User> getAll();
    void deleteAll();
    int getCount();
    void update(User user);
}
