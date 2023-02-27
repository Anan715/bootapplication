package com.alilang.stu.listener;

import com.alilang.stu.entity.Book;
import com.alilang.stu.entity.MyEvent;
import com.alilang.stu.service.BookService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyEventListener implements ApplicationListener<MyEvent> {

    @Resource
    private BookService bookService;

    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("Event Received: " + event.toString());
        Book book = new Book();
        book.setName(event.getBookName());
        book.setNumber(event.getBookId());
        bookService.save(book);

    }
}