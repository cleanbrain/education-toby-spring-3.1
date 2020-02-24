package springbook.user.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-01-28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/test-applicationContext.xml")
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;
    @Autowired
    PlatformTransactionManager transactionManager;
    @Autowired
    MailSender mailSender;

    List<User> users;

    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECCOMEND_FOR_GOLD = 30;

    static class MockMailSneder implements MailSender {
        private List<String> requests = new ArrayList<>();

        public List<String> getRequests() {
            return requests;
        }

        @Override
        public void send(SimpleMailMessage mailMessage) throws MailException {
            requests.add(mailMessage.getTo()[0]); // 전송 요청을 받은 이메일 주소를 저장해둔다. 간단하게 첫 번째 수신자 메일 주소만 저장했다.
        }

        @Override
        public void send(SimpleMailMessage... simpleMailMessages) throws MailException {

        }
    }

    @Before
    public void setUp() {
        users = Arrays.asList(
                new User("bumjin", "박범진", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER - 1, 0, "bumjin@test.co.kr"),
                new User("joytouch", "강명성", "p2", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0, "joytouch@test.co.kr"),
                new User("erwins", "신승한", "p3", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD - 1, "erwins@test.co.kr"),
                new User("madnite1", "이상호", "p4", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD, "madnite1@test.co.kr"),
                new User("green", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE, "green@test.co.kr")
        );
    }

    @Test
    public void bean() {
        assertThat(this.userService, is(notNullValue()));
        assertThat(this.userDao, is(notNullValue()));
    }

    @Test
    @DirtiesContext // 컨텍스트의 DI 설정을 변경하는 테스트라는 것을 알려준다.
    public void upgradeLevels() throws SQLException {
        userDao.deleteAll();

        for (User user : users)
            userDao.add(user);

        // 메일 발송 결과를 테스트할 수 있도록 목 오브젝트를 만들어 userService의 의존 오브젝트로 주입해준다.
        MockMailSneder mockMailSneder = new MockMailSneder();
        userService.setMailSender(mockMailSneder);

        userService.upgradeLevels();

        // 평시
        checkLevelUpgraded(users.get(0), false);
        checkLevelUpgraded(users.get(1), true);
        checkLevelUpgraded(users.get(2), false);
        checkLevelUpgraded(users.get(3), true);
        checkLevelUpgraded(users.get(4), false);

        // 이벤트
        /*checkLevelUpgraded(users.get(0), true);
        checkLevelUpgraded(users.get(1), true);
        checkLevelUpgraded(users.get(2), false);
        checkLevelUpgraded(users.get(3), true);
        checkLevelUpgraded(users.get(4), false);*/

        List<String> request = mockMailSneder.getRequests();
        assertThat(request.size(), is(2));
        assertThat(request.get(0), is(users.get(1).getEmail()));
        assertThat(request.get(1), is(users.get(3).getEmail()));
    }

    private void checkLevelUpgraded(User user, boolean upgraded) {
        User userUpdate = userDao.get(user.getId());

        if (upgraded) {
            assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
        } else {
            assertThat(userUpdate.getLevel(), is(user.getLevel()));
        }
    }

    @Test
    public void add() {
        userDao.deleteAll();

        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertThat(userWithLevel.getLevel(), is(userWithLevelRead.getLevel()));
        assertThat(userWithoutLevel.getLevel(), is(userWithoutLevelRead.getLevel()));
    }

    @Test
    public void upgradeAllOrNothing() throws SQLException {
        UserService testUserService = new TestUserService(users.get(3).getId());
        testUserService.setUserLevelUpgradePolicy(new UserLevelUpgradePolicyNormal());
        testUserService.setUserDao(this.userDao); // userDao 수동 DI
        testUserService.setTransactionManager(transactionManager);

        userDao.deleteAll();

        for (User user : users)
            userDao.add(user);

        try {
            testUserService.setMailSender(mailSender);
            testUserService.upgradeLevels();
            fail("TestUserSErviceException expected");
        } catch(TestUserService.TestUserServiceException e) {

        }

        checkLevelUpgraded(users.get(1), false);
    }

    public static void main(String[] args) {
        JUnitCore.main("springbook.user.service.UserServiceTest");
    }
}
