package cn.changming.consumer;

import com.rabbitmq.client.Channel;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

import cn.changming.constants.Constants;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class RabbitConsumer {

    @RabbitListener(queues = Constants.RABBIT_QUEUE_DISPATCH)
    public void dispatchOnMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag)
            throws IOException {
        try {
            log.info("派单服务请求接收器 - 接收到消息 - message={};deliveryTag={}", message, deliveryTag);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("派单服务请求接收器 - 接收到消息出现异常 - 异常信息={}", e.getMessage(), e);
        }
    }
}
