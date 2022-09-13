package com.example.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class JumpAspect {
    @Pointcut("execution(* com.example.MainActivity.jump(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public void aroundJump(ProceedingJoinPoint point) {
        try {
            Log.d("JumpAspect", "准备跳页");
            point.proceed();
            Log.d("JumpAspect", "完成跳页");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
