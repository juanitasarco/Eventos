package Paqueteria;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class main {
    public static void main(String[] args) throws TimeoutException, InterruptedException, IOException {
        OrderProducer producer = new OrderProducer();
        OrderConsumer consumer = new OrderConsumer();

        Thread consumerThread = new Thread(() -> {
            try {
                consumer.startListening();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        consumerThread.start();

        Thread.sleep(2000); // Espera de 2 segundos para asegurarnos de que el consumidor esté en línea antes de enviar un pedido
        Order order = new Order("124", "Juan Perez", "juanperez@example.com", "Calle 123 #27-23");
        producer.sendOrder(order);
        Thread.sleep(5000); // Espera de 2 segundos para dar tiempo al consumidor para procesar el mensaje
        System.exit(0);
    }
}

