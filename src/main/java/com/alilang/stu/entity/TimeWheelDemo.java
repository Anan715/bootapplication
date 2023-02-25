package com.alilang.stu.entity;

import java.util.ArrayList;
import java.util.List;

public class TimeWheelDemo {


    private static final int INTERVAL = 100;
    private static final int WHEEL_SIZE = 60;

    private List<TimerTaskList> wheel = new ArrayList<>(WHEEL_SIZE);
    private int interval;
    private int currentPos = 0;

    public TimeWheelDemo(int interval, int wheelSize) {
        this.interval = interval;
        for (int i = 0; i < wheelSize; i++) {
            wheel.add(new TimerTaskList());
        }
    }

    public void addTask(TimerTask task) {
        int tick = (int) (task.getDelay() / interval);
        int pos = (currentPos + tick) % WHEEL_SIZE;
        wheel.get(pos).addTask(task);
    }

    public void advanceClock() {
        TimerTaskList taskList = wheel.get(currentPos);
        taskList.advanceClock(interval);
        currentPos = (currentPos + 1) % WHEEL_SIZE;
    }

    private static class TimerTaskList {
        private List<TimerTask> tasks = new ArrayList<>();

        public void addTask(TimerTask task) {
            tasks.add(task);
        }

        public void advanceClock(int interval) {
            for (TimerTask task : tasks) {
                task.decreaseRemainingTime(interval);
                if (task.getRemainingTime() <= 0) {
                    task.run();
                }
            }
        }
    }


}