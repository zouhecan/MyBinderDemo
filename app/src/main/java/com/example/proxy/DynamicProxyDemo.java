package com.example.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * desc: 动态代理InvocationHandler
 * date: 2022/9/1
 */
public class DynamicProxyDemo {
    //被代理者
    private final MyLawSuit myLawSuit = new MyLawSuit();
    private final ClassLoader loader = myLawSuit.getClass().getClassLoader();
    private final DynamicProxy proxyHandler = new DynamicProxy(myLawSuit);
    private final Class[] interfaces = new Class[]{ILawsuit.class};
    //代理者
    private final ILawsuit lawyer = (ILawsuit) Proxy.newProxyInstance(loader, interfaces, proxyHandler);

    static {
        // 在工程目录下生成 $Proxy0 的 class 文件
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
    }

    //开始打官司
    public void startLawsuit() {
        lawyer.submit();
        lawyer.burden();
        lawyer.defend();
        lawyer.finish();
    }

    public void submit() {
        lawyer.submit();
    }

    public void burden() {
        lawyer.burden();
    }

    public void defend() {
        lawyer.defend();
    }

    public void finish() {
        lawyer.finish();
    }

    //动态代理
    class DynamicProxy implements InvocationHandler {
        //被代理对象
        private Object obj;

        DynamicProxy(Object obj) {
            this.obj = obj;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            return method.invoke(obj, objects);
        }
    }

    //打官司
    interface ILawsuit {
        //指控
        void submit();

        //举证
        void burden();

        //辩护
        void defend();

        //完成
        void finish();
    }

    //实体
    class MyLawSuit implements ILawsuit {
        private static final String TAG = "DynamicProxyDemo";

        @Override
        public void submit() {
            Log.d(TAG, "给我涨点工资");
        }

        @Override
        public void burden() {
            Log.d(TAG, "我一个人干两个人的活");
        }

        @Override
        public void defend() {
            Log.d(TAG, "摸摸你的良心");
        }

        @Override
        public void finish() {
            Log.d(TAG, "诉讼成功");
        }
    }

}
