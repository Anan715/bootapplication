package com.alilang.stu.controller;


import com.alilang.stu.entity.Book;
import com.alilang.stu.service.IBookService;
import com.alilang.stu.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IBookService bookService;

    @GetMapping("/book/list")
    public List<Book> getBookList(){
        return bookService.list();
    }


}

