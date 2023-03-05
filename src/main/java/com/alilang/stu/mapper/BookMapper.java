package com.alilang.stu.mapper;

import com.alilang.stu.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 图书表 Mapper 接口
 * </p>
 *
 * @author aLiLang
 * @since 2023-02-09
 */
public interface BookMapper extends BaseMapper<Book> {

    List<Book> listForUpdate(List<Long> ids);

}
