package springbook.learningtest.factorybean;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-02-26
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/FactoryBeanTest-context.xml")
public class FactoryBeanTest {
    @Autowired
    ApplicationContext context;

    @Test
    public void getMessageFromFactoryBean() {
        /**
         * FactoryBeanTest-context.xml 에 기술한 message 빈의 타입은 MessageFactoryBean 클래스 이지만
         * 실제 반환되는 빈은 Message 이다.
         * 실제 MessageFactoryBean 클래스를 빈으로 등록하고 싶은 경우에는 빈의 이름앞에 &를 붙인다.
         * Object message = context.getBean("&message");
         */
        Object message = context.getBean("message");
        assertThat((Message) message, is(Message.class));
        assertThat(((Message) message).getText(), is("Factory Bean"));
    }

    @Test
    public void getMessageFactoryBean() {
        Object message = context.getBean("&message");
        assertThat((MessageFactoryBean) message, is(MessageFactoryBean.class));
    }

    public static void main(String[] args) {
        JUnitCore.main("springbook.learningtest.factorybean.FactoryBeanTest");
    }
}
