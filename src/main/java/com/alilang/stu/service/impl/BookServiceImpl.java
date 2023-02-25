package com.alilang.stu.service.impl;

import com.alilang.stu.entity.Book;
import com.alilang.stu.mapper.BookMapper;
import com.alilang.stu.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {


    public void test(){
        List<Integer> list = new ArrayList<>();
        list.subList(0,Math.min(list.size(), 10));
    }
}
