package com.alilang.stu.transactionaldemo;

import com.alilang.stu.entity.Book;
import com.alilang.stu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TraTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private TraTest traTest;

    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void addBook1() {
        Book book = new Book();
        book.setId(9L).setNumber(9).setName("java");
        bookService.save(book);
        try {
            traTest.addBook2();
        } catch (Exception e) {
            System.out.println("e");
        }
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void addBook2() {

        Book book = new Book();
        book.setId(10L).setNumber(10).setName("C++");
        bookService.save(book);
        int i = 1 / 0;

    }
}
