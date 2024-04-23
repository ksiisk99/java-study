package design;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {
        Animal tigerProxy = (Animal)Proxy.newProxyInstance(
                Animal.class.getClassLoader(),
                new Class[] {Animal.class},
                new ProxyHandler(new Tiger())
        );

        tigerProxy.eat(5);
    }
}

interface Animal {
    void eat(int count);
}

class Tiger implements Animal {

    @Override
    public void eat(int count) {
        System.out.println("Tiger: " + count);
    }
}

class ProxyHandler implements InvocationHandler {
    private final Object target;

    public ProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy start");
        System.out.println(method);
        System.out.println(args);
        Object result = method.invoke(target, args);
        System.out.println("proxy end");

        return result;
    }
}
