package org.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CatInvocationHandler implements InvocationHandler {
    private final CatInterface origin;

    public CatInvocationHandler(CatInterface origin) {
        this.origin = origin;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Handler invoked");
        Object result = method.invoke(origin, args);
        System.out.println("Result = " + result);
        return result;
    }

    public static void main(String[] args) {
        Cat cat = new Cat();

        CatInterface proxyInstance = (CatInterface) Proxy.newProxyInstance(
                CatInvocationHandler.class.getClassLoader(),
                new Class[]{CatInterface.class},
                new CatInvocationHandler(cat)
        );

        CatInterface proxyInstance2 = (CatInterface) Proxy.newProxyInstance(
                CatInvocationHandler.class.getClassLoader(),
                new Class[]{CatInterface.class},
                new CatInvocationHandler2(proxyInstance)
        );
        proxyInstance2.getLength();
    }
}
