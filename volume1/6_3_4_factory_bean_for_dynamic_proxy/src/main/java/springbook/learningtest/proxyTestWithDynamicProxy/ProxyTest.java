package springbook.learningtest.proxyTestWithDynamicProxy;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.lang.reflect.Proxy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-02-20
 */
public class ProxyTest {
    @Test
    public void simpleProxy() {
        Hello hello = new HelloTarget();
        assertThat(hello.sayHello("Toby"), is("Hello Toby"));
        assertThat(hello.sayHi("Toby"), is("Hi Toby"));
        assertThat(hello.sayThankYou("Toby"), is("Thank You Toby"));
    }

    @Test
    public void simpleProxy2() {
        Hello proxiedHello = new HelloUppercase(new HelloTarget()); // 프록시를 통해 타깃 오브젝트에 접근하도록 구성한다.
        assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
        assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
        assertThat(proxiedHello.sayThankYou("Toby"), is("THANK YOU TOBY"));
    }

    @Test
    public void dynamicproxy() {
        Hello proxiedHello = (Hello) Proxy.newProxyInstance(getClass().getClassLoader(),
                                                            new Class[] {Hello.class},
                                                            new UppercaseHandler(new HelloTarget()));
        assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
        assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
        assertThat(proxiedHello.sayThankYou("Toby"), is("THANK YOU TOBY"));
    }

    public static void main(String[] args) {
        JUnitCore.main("springbook.learningtest.proxyTestWithDynamicProxy.ProxyTest");
    }
}
