package com.beibei.design.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class EngineerProxyDynamic implements InvocationHandler {
    Object obj;

    public Object bind(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Engineer writes document...");
        Object res = method.invoke(obj, args);
        return res;
    }
}