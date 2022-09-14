package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.aidl.AIDLTestActivity;
import com.example.ipc.client.BinderClientActivity;
import com.example.proxy.DynamicProxyDemoActivity;
import com.example.proxy.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    private Unbinder unbinder;

    @BindView(R.id.proxyBtn)
    Button proxyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        proxyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(DynamicProxyDemoActivity.class);
            }
        });
        findViewById(R.id.binderBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(BinderClientActivity.class);
            }
        });
        findViewById(R.id.aidlBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(AIDLTestActivity.class);
            }
        });
    }

    public void jump(Class clazz) {
        reportName(clazz);
        Intent intent = new Intent(MainActivity.this, clazz);
        startActivity(intent);
    }

    private void reportName(Class clazz) {
        Log.d("JumpAspect", "来着留名" + clazz.getCanonicalName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}