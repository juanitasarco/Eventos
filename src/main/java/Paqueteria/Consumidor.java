package Paqueteria;

import com.rabbitmq.client.*;
import org.springframework.amqp.utils.SerializationUtils;


public class Consumidor {
    private final static String NombreCola = "order_1";

    public void startListening() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(NombreCola, false, false, false, null);
        System.out.println("Esperando por mensajes de pedidos...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            DatosOrden order = (DatosOrden) SerializationUtils.deserialize(delivery.getBody());
            System.out.println("Recibido pedido: " + order.obtenerIdOrden() + ". Entregado en: " + order.obtenerDireccion());
        };
        channel.basicConsume(NombreCola, true, deliverCallback, consumerTag -> {
        });
    }
}

