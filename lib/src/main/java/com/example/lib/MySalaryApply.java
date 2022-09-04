package com.example.lib;

public class MySalaryApply implements ISalaryApply{
    @Override
    public void submit() {
        System.out.println("老板，我想涨工资");
    }

    @Override
    public void reason() {
        System.out.println("因为我一个人干两个人的活");
    }

    @Override
    public void result() {
        System.out.println("老板不同意，爱干不干");
    }
}
