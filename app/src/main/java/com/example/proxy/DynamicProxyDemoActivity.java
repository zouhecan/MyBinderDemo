package com.example.proxy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * desc: 动态代理 演示
 * date: 2022/9/1
 */
public class DynamicProxyDemoActivity extends AppCompatActivity {
    private Status status = Status.SUBMIT;

    enum Status {
        SUBMIT,
        BURDEN,
        DEFEND,
        FINISH
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DynamicProxyDemo proxy = new DynamicProxyDemo();
        setContentView(R.layout.activity_dynamic_proxy_layout);
        Button content = findViewById(R.id.content);
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status == Status.SUBMIT) {
                    content.setText("提交诉讼：给我发的钱太少了");
                    proxy.submit();
                    status = Status.BURDEN;
                } else if (status == Status.BURDEN) {
                    content.setText("举证：我一个人干两个人的活");
                    status = Status.DEFEND;
                    proxy.burden();
                } else if (status == Status.DEFEND) {
                    content.setText("辩护：请摸摸你的良心");
                    status = Status.FINISH;
                    proxy.defend();
                } else {
                    content.setText("诉讼成功");
                    proxy.finish();
                }
            }
        });
    }
}