package co.com.sofka.rabbitmq;

import co.com.sofka.model.gateways.RabbitGateway;
import co.com.sofka.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageRabbit implements RabbitGateway {

    private final AmqpTemplate amqpTemplate;
    private static final String EXCHANGE_NAME = "direct.ex";
    private static final String ROUTING_KEY = "transaction.key";

    @Override
    public Mono<String> sendMessage(Transaction transaction) {
        amqpTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, convertObjectToJsonString(transaction));
        return Mono.just("Message Sent");
    }

    public static String convertObjectToJsonString(Transaction transaction) {
        JSONObject jsonObject = new JSONObject(transaction);
        return jsonObject.toString();
    }

}
