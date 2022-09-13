package com.example.lib.proxy;

import java.lang.reflect.Proxy;

/**
 * desc: 动态代理
 * date: 2022/9/1
 */
public class DynamicProxyDemo {
    //被代理对象
    private final MySalaryApply myApply = new MySalaryApply();
    private final ClassLoader loader = myApply.getClass().getClassLoader();
    private final SalaryApplyProxyHandler proxyHandler = new SalaryApplyProxyHandler(myApply);
    private final Class[] interfaces = new Class[]{ISalaryApply.class};
    //代理对象
    private final ISalaryApply proxy = (ISalaryApply) Proxy.newProxyInstance(loader, interfaces, proxyHandler);

    static {
        // 在工程目录下生成 $Proxy0 的 class 文件
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
    }

    public void startApply() {
        proxy.submit();
        proxy.reason();
        proxy.result();
    }

}
