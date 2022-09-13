package com.example.lib.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SalaryApplyProxyHandler implements InvocationHandler {
    //被代理实体对象
    private ISalaryApply apply;

    public SalaryApplyProxyHandler(ISalaryApply apply) {
        this.apply = apply;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return method.invoke(apply, objects);
    }
}
