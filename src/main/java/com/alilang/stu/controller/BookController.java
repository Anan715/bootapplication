package com.alilang.stu.controller;


import com.alilang.stu.entity.Book;
import com.alilang.stu.service.BookService;
import com.alilang.stu.transactionaldemo.ThreadInsertTest;
import com.alilang.stu.transactionaldemo.TraTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 图书表 前端控制器
 * </p>
 *
 * @author aLiLang
 * @since 2023-02-09
 */
@RestController
@RequestMapping("/stu/book")
public class BookController {

    @Resource
    private BookService bookService;
    @Resource
    private TraTest traTest;
    @Resource
    private ThreadInsertTest threadInsertTest;

    @GetMapping("/book/list")
    public List<Book> getBookList() {
        return bookService.list();
    }

    @GetMapping("/tra/test")
    public void traTest() {
        traTest.addBook1();
    }

    @GetMapping("/thread/insert")
    public void threadTest() {
        threadInsertTest.batchHandle();
    }

    @GetMapping("/update/no/lock")
    public void updateNoLock() {
        bookService.updateBook();
    }


    @GetMapping("/update/with/lock")
    public void updateWithLock() {
        bookService.updateBookWithLock();
    }


}

