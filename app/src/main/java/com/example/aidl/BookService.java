package com.example.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * 跨进程服务
 */
public class BookService extends Service {
    private List<Book> list = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book();
        book.setName("磊锅子");
        book.setPrice(666);
        list.add(book);
    }

    private IBinder binder = new IBookInterface.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return list;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            list.add(book);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
