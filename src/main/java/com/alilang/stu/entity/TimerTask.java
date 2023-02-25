package com.alilang.stu.entity;

public  class TimerTask {
    private long remainingTime;
    private Runnable task;

    public TimerTask(long delay, Runnable task) {
        this.remainingTime = delay;
        this.task = task;
    }

    public void run() {
        task.run();
    }

    public void decreaseRemainingTime(int interval) {
        remainingTime -= interval;
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public long getDelay() {
        return remainingTime;
    }
}