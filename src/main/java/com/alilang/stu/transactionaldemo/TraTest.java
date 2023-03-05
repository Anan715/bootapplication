package com.alilang.stu.transactionaldemo;

import com.alilang.stu.entity.Book;
import com.alilang.stu.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class TraTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private TraTest traTest;

    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void addBook1() {
        List<Book> saveList = new ArrayList();

        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A",1);
        map1.put("B",1);
        map1.put("C",1);
        map1.put("D",1);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("F",1);
        map2.put("B",1);
        map2.put("C",1);
        map2.put("D",1);

        System.out.println(map1.size());
        map1.keySet().removeAll(map2.keySet());
        System.out.println(map1.size());
        System.out.println(map1.keySet());





        for (int i = 0; i < 5; i++) {

            Book book = new Book();
            book.setNumber(i).setName("java" + i);
            saveList.add(book);
        }
        bookService.saveBatch(saveList);
        log.info("入库完毕");

    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void addBook2() {

        Book book = new Book();
        book.setId(10L).setNumber(10).setName("C++");
        bookService.save(book);
        int i = 1 / 0;

    }
}
