package com.zlx.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author zlx
 * @Date 2024/5/13 15:57
 */
@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleMessage(String message) {
        System.out.println("SpringRabbitListener.listenSimpleMessage: " + message);
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueueMessage1(String message) throws InterruptedException {
        System.out.println("消费者1： " + message);
        Thread.sleep(20);
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueueMessage2(String message) throws InterruptedException {
        System.out.println("消费者2： " + message);
        Thread.sleep(200);
    }
}
