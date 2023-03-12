package Paqueteria;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.utils.SerializationUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class OrderProducer {
    private final static String QUEUE_NAME = "order_queue";

    public void sendOrder(Order order) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, SerializationUtils.serialize(order));
            System.out.println("Enviado pedido: " + order.getOrderId());
        }
    }
}

