package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aidl.AIDLTestActivity;
import com.example.ipc.client.BinderClientActivity;
import com.example.proxy.DynamicProxyDemoActivity;
import com.example.proxy.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.proxyBtn)
    Button proxyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        proxyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DynamicProxyDemoActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.binderBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BinderClientActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.aidlBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AIDLTestActivity.class);
                startActivity(intent);
            }
        });
    }
}