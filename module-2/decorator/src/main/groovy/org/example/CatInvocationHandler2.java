package org.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CatInvocationHandler2 implements InvocationHandler {
    private final CatInterface origin;

    public CatInvocationHandler2(CatInterface origin) {
        this.origin = origin;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Handler 2 invoked");
        Object result = method.invoke(origin, args);
        System.out.println("Result 2 = " + result);
        return result;
    }
}
