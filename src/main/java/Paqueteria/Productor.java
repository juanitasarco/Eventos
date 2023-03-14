package Paqueteria;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.utils.SerializationUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Productor {
    private final static String NombreCola = "order_1";

    public void enviarOrden(DatosOrden order) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(NombreCola, false, false, false, null);
            channel.basicPublish("", NombreCola, null, SerializationUtils.serialize(order));
            System.out.println("Enviado pedido: " + order.obtenerIdOrden());
        }
    }
}

