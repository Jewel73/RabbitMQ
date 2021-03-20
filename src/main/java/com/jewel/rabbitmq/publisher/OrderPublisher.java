package com.jewel.rabbitmq.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jewel.rabbitmq.config.MessageConfig;
import com.jewel.rabbitmq.model.Order;
import com.jewel.rabbitmq.model.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
	
	@Autowired
	private RabbitTemplate temlate;
	
	@PostMapping("/{name}")
	public String bookOrder(@RequestBody Order order, @PathVariable String name) {
		order.setOrderId(UUID.randomUUID().toString());
		// resutarant service
		// other service
		
		OrderStatus orderStatus = new OrderStatus(order, "Process", "This is the Test Message");
		temlate.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, orderStatus);
		return "Success";
	}
}
