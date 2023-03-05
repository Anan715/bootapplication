package com.alilang.stu.service.impl;

import com.alilang.stu.entity.Book;
import com.alilang.stu.mapper.BookMapper;
import com.alilang.stu.service.BookService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 图书表 服务实现类
 * </p>
 *
 * @author aLiLang
 * @since 2023-02-09
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {


    public void test() {
        List<Integer> list = new ArrayList<>();
        list.subList(0, Math.min(list.size(), 10));
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Book::getId, 1);
        wrapper.last("for update");
    }

    @Override
    @Transactional
    public void updateBook() {
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Book::getId, 1);
        Book one = this.getOne(wrapper);
        one.setName("前端指南");
        this.updateById(one);
    }

    @Override
    @Transactional
    public void updateBookWithLock() {
        List<Long> ids = new ArrayList();
        ids.add(1l);
        ids.add(2l);
        ids.add(3l);
        ids.add(4l);
        List<Book> books = baseMapper.listForUpdate(ids);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        books.stream().forEach(i -> i.setName("JAVA思想"));
        this.updateBatchById(books);

    }
}
