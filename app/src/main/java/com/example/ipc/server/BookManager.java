package com.example.ipc.server;


import android.os.IInterface;
import android.os.RemoteException;

import com.example.ipc.Book;

import java.util.List;

/**
 * 这个类用来定义服务端 RemoteService 具备什么样的能力
 */
public interface BookManager extends IInterface {

    List<Book> getBooks() throws RemoteException;

    void addBook(Book book) throws RemoteException;
}
