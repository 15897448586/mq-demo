package com.zlx.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author zlx
 * @Date 2024/5/13 15:47
 */
@SpringBootTest
public class SimpleTest {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testSimpleQueue(){
        String queue = "simple.queue";
        String message = "hello,you're very nice!!!";
        rabbitTemplate.convertAndSend(queue, message);
    }

    @Test
    public void testWorkQueue() throws InterruptedException {
        String queue = "work.queue";
        String message = "I'm message_";
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(queue, message + i);
            Thread.sleep(20);
        }
    }
}
