// IBookInterface.aidl
package com.example.aidl;

// Declare any non-default types here with import statements
import com.example.aidl.Book;
parcelable Book;

interface IBookInterface {
 List<com.example.aidl.Book> getBookList();
 void addBook(in com.example.aidl.Book book);
}