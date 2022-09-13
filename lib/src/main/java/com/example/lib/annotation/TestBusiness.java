package com.example.lib.annotation;

import java.lang.reflect.Field;

/**
 * desc:
 * date: 2022/9/13
 */
public class TestBusiness {
    private TestModel model = new TestModel();

    public void handleData() {
        try {
            System.out.println("处理注解开始");
            Class clazz = model.getClass();
            for (Field f : clazz.getDeclaredFields()) {
                if (f.isAnnotationPresent(MyField.class)) {
                    MyField myField = (MyField) f.getAnnotation(MyField.class);
                    assert myField != null;
                    String description = myField.description();
                    System.out.println("处理注解完成");
                }
            }
        } catch (Exception e) {
            System.out.println("处理注解异常" + e.getMessage());
        }
    }
}
