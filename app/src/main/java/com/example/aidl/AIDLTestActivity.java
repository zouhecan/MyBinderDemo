package com.example.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.proxy.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * desc: 通过AIDL自动实现IPC client
 * date: 2022/9/1
 */
public class AIDLTestActivity extends AppCompatActivity {
    private String TAG = "AIDLDemo";
    private IBookInterface binder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = IBookInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidltest);
        findViewById(R.id.addBook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("Android学习日记");
                book.setPrice(100);
                try {
                    binder.addBook(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.getBook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<Book> list = binder.getBookList();
                    for (Book book : list) {
                        Log.d(TAG, book.getName() + "这版本书值" + book.getPrice() + "块钱");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        binService();
    }

    private void binService() {
        Intent intent = new Intent(this, BookService.class);
        intent.setAction("com.example.aidl");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
}