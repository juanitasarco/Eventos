package Paqueteria;

import com.rabbitmq.client.GetResponse;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EventPatternApplicationTests {

	public class RabbitMQOrderSenderTest {

		private ConnectionFactory factory;
		private Connection connection;
		private Channel channel;

		@Before
		public void setUp() throws IOException, TimeoutException {
			// Configuramos la conexión a RabbitMQ
			factory = new ConnectionFactory();
			factory.setHost("localhost");
			connection = factory.newConnection();
			channel = connection.createChannel();

			// Declaramos la cola en la que se enviarán las órdenes
			channel.queueDeclare("order_queue", false, false, false, null);
		}

		@After
		public void tearDown() throws IOException, TimeoutException {
			// Cerramos la conexión a RabbitMQ
			channel.close();
			connection.close();
		}

		@Test
		public void testSendOrder() throws IOException {
			// Enviamos una orden a la cola
			String order = "{ \"id\": 1, \"product\": \"Smartphone\", \"quantity\": 2 }";
			channel.basicPublish("", "order_queue", null, order.getBytes());

			// Verificamos que la orden se haya enviado correctamente
			GetResponse response = channel.basicGet("order_queue", true);
			assertNotNull(response);
			assertEquals(order, new String(response.getBody()));
		}
	}

