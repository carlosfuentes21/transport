package co.com.sofka.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    private static final String QUEUE_NAME = "transaction.ex";

    @Bean
    public Queue createQueue() {
        return new Queue(QUEUE_NAME, true);
    }

}
