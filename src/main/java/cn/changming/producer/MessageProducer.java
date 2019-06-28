package cn.changming.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.changming.constants.Constants;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class MessageProducer {
    @Autowired
    protected RabbitTemplate rabbitTemplate;

    public void sendMessage(Object object) {
        this.rabbitTemplate.convertAndSend(Constants.RABBIT_DIRECT_EXCHANGE_NAME, Constants.RABBIT_BIND_KEY_DISPATCH,
                object);
        log.info("RabbitMQ发送消息 - 向mq中存放数据成功 -object={}", object);
    }

}
