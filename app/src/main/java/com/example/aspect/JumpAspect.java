package com.example.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class JumpAspect {
    //指定切点的位置在com.example.MainActivity.jump()方法
    @Pointcut("execution(* *(..)) && cflow(execution(* com.example.MainActivity.jump(..)))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public void aroundJump(ProceedingJoinPoint point) {
        try {
            Signature signature = point.getSignature();
            //在com.example.MainActivity.jump()执行前
            Log.d("JumpAspect", signature + "准备跳页");

            //com.example.MainActivity.jump()执行
            point.proceed();

            //在com.example.MainActivity.jump()执行后
            Log.d("JumpAspect", signature + "完成跳页");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
