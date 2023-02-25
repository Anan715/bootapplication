package com.alilang.stu.controller;


import com.alilang.stu.entity.Book;
import com.alilang.stu.entity.MyEvent;
import com.alilang.stu.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
public class EventController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/event/trigger")
    public String triggerEvent(){

        publisher.publishEvent(new MyEvent(this, 1, "参数详解"));
        return "Event Triggered";
    }


}

