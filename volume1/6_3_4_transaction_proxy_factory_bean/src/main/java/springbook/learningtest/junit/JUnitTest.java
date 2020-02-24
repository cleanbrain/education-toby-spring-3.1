package springbook.learningtest.junit;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-01-06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/springbook/learningtest/junit/junit.xml")
public class JUnitTest {
    @Autowired
    ApplicationContext context;
    static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();
    static ApplicationContext contextObject = null;
//    static JUnitTest testObject;

    @Test
    public void test1() {
//        assertThat(this, is(not(sameInstance(testObject))));
//        testObject = this;

        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);

        assertThat(contextObject == null || contextObject == this.context, is(true)); // method 1
        contextObject = this.context;
    }

    @Test
    public void test2() {
//        assertThat(this, is(not(sameInstance(testObject))));
//        testObject = this;

        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);

        assertTrue(contextObject == null || contextObject == this.context); // method 2
        contextObject = this.context;
    }

    @Test
    public void test3() {
//        assertThat(this, is(not(sameInstance(testObject))));
//        testObject = this;

        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);

        assertThat(contextObject, either(is(nullValue())).or(is(this.context))); // method 3
        contextObject = this.context;
    }

    public static void main(String[] args) {
        JUnitCore.main("springbook.learningtest.junit.JUnitTest");
    }
}
