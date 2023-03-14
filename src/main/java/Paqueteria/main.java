package Paqueteria;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class main {
    public static void main(String[] args) throws TimeoutException, InterruptedException, IOException {
        Productor producer = new Productor();
        Consumidor consumer = new Consumidor();

        Thread consumerThread = new Thread(() -> {
            try {
                consumer.startListening();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        consumerThread.start();

        Thread.sleep(5000);
        DatosOrden order = new DatosOrden("12", "Juan Perez", "juanperez@gmail.com", "Calle 123 #27-23");
        producer.enviarOrden(order);
    }
}

