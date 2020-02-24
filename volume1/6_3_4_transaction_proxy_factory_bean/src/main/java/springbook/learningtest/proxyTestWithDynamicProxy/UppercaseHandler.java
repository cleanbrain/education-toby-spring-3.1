package springbook.learningtest.proxyTestWithDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-02-20
 */
/*public class UppercaseHandler implements InvocationHandler {
    Hello target;

    // 다이내믹 프록시로부터 전달받은 요청을 다시 타깃 오브젝트에 위임해야 하기 때문에 타깃 오브젝트를 주입받아 둔다.
    public UppercaseHandler(Hello target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 타깃으로 위임. 인터페이스의 메소드 호출에 모두 적용된다.
        String ret = (String) method.invoke(target, args);
        // 부가기능 제공
        return ret.toUpperCase();
    }
}*/

// Hello 인터페이스 말고도 모든 종류의 인터페이스 수용 가능하도록 변경
// 반환 받은 Object의 타입이 String 이면 대문자 적용
public class UppercaseHandler implements InvocationHandler {
    Object target;

    public UppercaseHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret = method.invoke(target, args);

        if (ret instanceof String) {
            return ((String) ret).toUpperCase();
        } else {
            return ret;
        }

        // 이런식으로 메소드 이름으로도 분기문을 태울 수 있다.
        /*if (ret instanceof String && method.getName().startsWith("say")) {
            return ((String) ret).toUpperCase();
        }*/
    }
}