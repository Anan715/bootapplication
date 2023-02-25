package com.alilang.stu.entity;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {

    private Integer bookId;

    private String bookName;


    public MyEvent(Object source, Integer bookId, String bookName) {
        super(source);
        this.bookId = bookId;
        this.bookName = bookName;

    }

    public Integer getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}