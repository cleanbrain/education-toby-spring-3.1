package springbook.learningtest.proxyTestWithDynamicProxy;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-02-20
 */
public class HelloTarget implements Hello {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public String sayHi(String name) {
        return "Hi " + name;
    }

    @Override
    public String sayThankYou(String name) {
        return "Thank You " + name;
    }
}
