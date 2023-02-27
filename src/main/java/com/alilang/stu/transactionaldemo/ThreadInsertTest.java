package com.alilang.stu.transactionaldemo;

import com.alilang.stu.entity.Book;
import com.alilang.stu.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class ThreadInsertTest {

    @Resource
    private BookService bookService;
    //自定义事务管理器
    @Resource
    private SelfTransactionManager selfTransactionManager;
    //子线程是否能进行提交
    public static volatile boolean IS_OK = true;

    public void batchHandle() {
        //主线程等待所有子线程执行完成
        int threadCount = 5;
        CountDownLatch childMonitor = new CountDownLatch(threadCount);
        //主线程收集到的子线程最终结果
        List<Boolean> childResponse = new ArrayList<Boolean>();
        //子线程在该对象上等待主线程通知
        CountDownLatch mainMonitor = new CountDownLatch(1);
        ExecutorService executor = Executors.newCachedThreadPool();
        final int j = 3;
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            executor.execute(() -> {
                //开启事务
                selfTransactionManager.begin();
                try {
                    bookService.saveBatch(buildBookList());
                    int k = 1/(j- finalI);
                    childResponse.add(Boolean.TRUE);
                    childMonitor.countDown();
                    log.info("线程{}正常执行完成,等待其他线程执行结束，判断是否需要回滚", Thread.currentThread().getName());
                    mainMonitor.await();
                    if (IS_OK) {
                        log.info("所有线程都正常完成，线程{}事务提交", Thread.currentThread().getName());
                        selfTransactionManager.commit();
                    } else {
                        log.info("有线程出现异常,线程{}事务回滚", Thread.currentThread().getName());
                        selfTransactionManager.rollBack();
                    }
                } catch (Exception e) {
                    childResponse.add(Boolean.FALSE);
                    childMonitor.countDown();
                    log.error("线程{}发生了异常,开始进行事务回滚", Thread.currentThread().getName());
                    selfTransactionManager.rollBack();
                }
            });
        }
        try {
            //主线程等待所有子线程执行完成
            childMonitor.await();
            for (Boolean resp : childResponse) {
                if (!resp) {
                    //如果有一个子线程执行失败了，则改变mainResult，让所有子线程回滚
                    log.info("{}:IS_OK的值被修改为false", Thread.currentThread().getName());
                    IS_OK = false;
                    break;
                }
            }
            //主线程获取结果成功，让子线程开始根据主线程的结果执行(提交或回滚
            mainMonitor.countDown();
        } catch (Exception e) {
            log.info(e.getCause().getMessage());
        }

    }

    private static List<Book> buildBookList() {
        List<Book> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Book book = new Book();
            book.setName("why" + i);
            book.setNumber(i);
            list.add(book);
        }
        log.info("数据批量新建完成");
        return list;
    }
}