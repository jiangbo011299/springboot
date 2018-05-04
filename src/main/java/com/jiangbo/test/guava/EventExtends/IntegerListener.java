package com.jiangbo.test.guava.EventExtends;

import com.google.common.eventbus.Subscribe;

/**
 * Integer extends Number
 */
public class IntegerListener {

    private Integer lastMessage;

    @Subscribe
    public void listen(Integer integer) {
        lastMessage = integer;
        System.out.println("Message:"+lastMessage);
    }

    public Integer getLastMessage() {
        return lastMessage;
    }
}
