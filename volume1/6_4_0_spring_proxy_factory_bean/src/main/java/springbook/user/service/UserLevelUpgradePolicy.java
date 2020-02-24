package springbook.user.service;

import springbook.user.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-01-28
 */
public interface UserLevelUpgradePolicy {
    boolean canUpgradeLevel(User user);
    void upgradeLevel(User user);
}
