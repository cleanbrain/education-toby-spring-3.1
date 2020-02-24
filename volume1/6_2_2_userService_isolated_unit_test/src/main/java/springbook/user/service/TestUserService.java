package springbook.user.service;

import springbook.user.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-01-31
 */
public class TestUserService extends UserServiceImpl {
    private String id;

    public TestUserService(String id) {
        this.id = id;
    }

    protected void upgradeLevel(User user) {
        if (user.getId().equals(this.id))
            throw new TestUserServiceException();
        super.upgradeLevel(user);
    }

    static class TestUserServiceException extends RuntimeException {
    }
}
