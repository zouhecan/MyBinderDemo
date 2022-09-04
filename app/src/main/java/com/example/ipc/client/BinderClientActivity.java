package com.example.ipc.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.ipc.Book;
import com.example.ipc.server.BookManager;
import com.example.ipc.server.RemoteService;
import com.example.ipc.server.Stub;
import com.example.proxy.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * desc: Binder手动实现IPC client
 * date: 2022/9/1
 */
public class BinderClientActivity extends AppCompatActivity {
    private String TAG = "BinderIPCDemo";
    private BookManager bookManager;
    private boolean isConnection = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isConnection) {
                    attemptToBindService();
                    return;
                }

                if (bookManager == null)
                    return;

                try {
                    Book book = new Book();
                    book.setPrice(101);
                    book.setName("编码");
                    bookManager.addBook(book);

                    Log.d(TAG, bookManager.getBooks().toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });


        findViewById(R.id.getBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isConnection) {
                    attemptToBindService();
                    return;
                }

                if (bookManager == null)
                    return;

                try {
                    List<Book> list = bookManager.getBooks();
                    for (Book book : list) {
                        Log.d(TAG, book.getName() + "这版本书值" + book.getPrice() + "块钱");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void attemptToBindService() {
        Intent intent = new Intent(this, RemoteService.class);
        intent.setAction("com.example.ipc");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnection = true;
            bookManager = Stub.asInterface(service);
            if (bookManager != null) {
                try {
                    List<Book> books = bookManager.getBooks();
                    Log.d("ClientActivity", books.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnection = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if (!isConnection) {
            attemptToBindService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isConnection) {
            unbindService(serviceConnection);
        }
    }
}
