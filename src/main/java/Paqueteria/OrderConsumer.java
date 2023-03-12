package Paqueteria;

import com.rabbitmq.client.*;
import org.springframework.amqp.utils.SerializationUtils;
//import org.apache.commons.lang3.SerializationUtils;
import java.io.IOException;

public class OrderConsumer {
    private final static String QUEUE_NAME = "order_queue";

    public void startListening() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Esperando por mensajes de pedidos...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            Order order = (Order) SerializationUtils.deserialize(delivery.getBody());
            System.out.println("Recibido pedido: " + order.getOrderId());
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
    }
}

