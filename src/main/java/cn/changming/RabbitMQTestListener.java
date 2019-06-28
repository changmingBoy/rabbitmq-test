package cn.changming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import cn.changming.producer.MessageProducer;
import lombok.extern.log4j.Log4j2;

@Log4j2
//@Component
public class RabbitMQTestListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private MessageProducer messageProducer;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        threadPoolTaskExecutor.execute(() -> {
            try {
                int max = 2;
                int start = 1;
                while (start <= max) {
                    Thread.sleep(1000);
                    messageProducer.sendMessage("哈哈" + start);
                    start++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
