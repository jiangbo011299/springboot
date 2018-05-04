package com.jiangbo.test.guava.single;

/**
 * 消息封装类：
 */
public class TestEvent {

    private final int message;

    public TestEvent(int message) {
        this.message = message;
        System.out.println("event message:"+message);
    }

    public int getMessage() {
        return message;
    }
}
