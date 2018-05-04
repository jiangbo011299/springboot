package com.jiangbo.test.guava.dead;

import com.google.common.eventbus.EventBus;
import com.jiangbo.test.guava.single.TestEvent;
import org.junit.jupiter.api.Test;

/**
 * 说明：如果没有消息订阅者监听消息， EventBus将发送DeadEvent消息，这时我们可以通过log的方式来记录这种状态。
 */
public class TestDeadEventListeners {

    @Test
    public void testDeadEventListeners() throws Exception {

        EventBus eventBus = new EventBus("test");
        DeadEventListener deadEventListener = new DeadEventListener();
        eventBus.register(deadEventListener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));

        System.out.println("deadEvent:"+deadEventListener.isNotDelivered());

    }
}
