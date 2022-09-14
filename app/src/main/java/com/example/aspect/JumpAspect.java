package com.example.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class JumpAspect {
    //指定切点的位置在com.example.MainActivity.jump()方法
    @Pointcut("execution(* com.example.MainActivity.jump(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public void aroundJump(ProceedingJoinPoint point) {
        try {
            //在com.example.MainActivity.jump()执行前
            Log.d("JumpAspect", "准备跳页");

            //com.example.MainActivity.jump()执行
            point.proceed();

            //在com.example.MainActivity.jump()执行后
            Log.d("JumpAspect", "完成跳页");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
