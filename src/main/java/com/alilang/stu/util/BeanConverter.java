package com.alilang.stu.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class BeanConverter<T, R> {

    public static <T, R> R convert(T source, Class<R> clazz) {
        if (source == null) {
            return null;
        }
        R target = null;
        try {
            target = clazz.newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("bean转换失败", e);
        }
        return target;
    }

    public static <T, R> List<R> convertList(List<T> source, Class<R> clazz) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }
        return source.stream().map(i -> convert(i, clazz)).collect(Collectors.toList());
    }

    public static <T, R> Page<R> convertPageData(Page<T> page, Class<R> clazz) {
        Page<R> resp = new Page<>();
        resp.setRecords(convertList(page.getRecords(), clazz));
        resp.setCurrent(page.getCurrent());
        resp.setSize(page.getSize());
        resp.setTotal(page.getTotal());
        resp.setPages(page.getPages());
        return resp;
    }

    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> Obj2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                log.error("object转化为map异常，e={}", e.getMessage());
            }
        }
        return map;
    }

    //不做数据转换，手动转换
    public static <T, R> IPage<R> convertPaged(IPage<T> iPage, Class<R> clazz) {
        Page<R> resp = new Page<>();
        resp.setRecords(convertList(iPage.getRecords(), clazz));
        resp.setCurrent(iPage.getCurrent());
        resp.setSize(iPage.getSize());
        resp.setTotal(iPage.getTotal());
        resp.setPages(iPage.getPages());
        return resp;
    }

}
