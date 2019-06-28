package cn.changming.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import cn.changming.constants.Constants;

@Configuration
public class RabbitConfig {

    private static final Map<String, Object> ARGUMENTS = new HashMap<>();

    static {
        ARGUMENTS.put("x-ha-policy", "all");
    }

    @Bean
    public Queue dispatchQueue() {
        return new Queue(Constants.RABBIT_QUEUE_DISPATCH, true, false, false, ARGUMENTS);
    }


    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(Constants.RABBIT_DIRECT_EXCHANGE_NAME);
    }

    @Bean
    public Binding dispatchBinding(@Qualifier("dispatchQueue") Queue dispatchQueue,
                                   @Qualifier("defaultExchange") DirectExchange defaultExchange) {
        return BindingBuilder.bind(dispatchQueue).to(defaultExchange).with(Constants.RABBIT_BIND_KEY_DISPATCH);
    }
}