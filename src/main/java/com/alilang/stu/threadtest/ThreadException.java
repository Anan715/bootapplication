package com.alilang.stu.threadtest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadException {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Boolean> future = pool.submit(() -> {
            log.info("running");
            int i = 1 / 0;
            return true;
        });
        try {
            log.info("future get: {}", future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
