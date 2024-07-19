package co.com.sofka.rabbitmq;

import co.com.sofka.model.Transaction;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class MessageRabbitTest {

    @Mock
    private AmqpTemplate amqpTemplate;

    @InjectMocks
    private MessageRabbit messageRabbit;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendMessage() {
        Transaction transaction = Transaction.builder().build();
        String jsonTransaction = new JSONObject(transaction).toString();

        doNothing().when(amqpTemplate).convertAndSend(anyString(), anyString(), anyString());

        StepVerifier.create(messageRabbit.sendMessage(transaction))
                .expectNext("Message Sent")
                .verifyComplete();

        verify(amqpTemplate, times(1)).convertAndSend("direct.ex", "transaction.key", jsonTransaction);
    }
}