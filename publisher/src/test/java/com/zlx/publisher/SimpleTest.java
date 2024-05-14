package com.zlx.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void testFanoutExchange() {
        String exchange = "hmall.fanout";

        String message = "hello,you're very very very nice!!!";

        rabbitTemplate.convertAndSend(exchange, "", message);
    }


    @Test
    public void testSendDirectExchange() {
        // 交换机名称
        String exchangeName = "hmall.direct";
        // 消息
        String message = "红色警报！日本乱排核废水，导致海洋生物变异，惊现哥斯拉！";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "red", message);
        rabbitTemplate.convertAndSend(exchangeName, "blue", message + "666");
    }

    @Test
    public void testSendTopicExchange() {
        // 交换机名称
        String exchangeName = "hmall.topic";
        // 消息
        String message = "喜报！孙悟空大战哥斯拉，胜!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "china.news", message);
    }

    @Test
    public void testSendMap() throws InterruptedException {
        // 准备消息
        Map<String,Object> msg = new HashMap<>();
        msg.put("name", "柳岩");
        msg.put("age", 21);
        // 发送消息
        rabbitTemplate.convertAndSend("object.queue", msg);
    }
}
