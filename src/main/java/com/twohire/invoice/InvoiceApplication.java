package com.twohire.invoice;

import com.twohire.invoice.model.Payment;
import com.twohire.invoice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.SubscribableChannel;

import java.util.Map;

interface ConsumerChannels {
	@Input
	SubscribableChannel producer();
}

@SpringBootApplication
@EnableBinding(ConsumerChannels.class)
public class InvoiceApplication {

	@Autowired
	private PaymentService paymentService;

	public static void main(String[] args) {
		SpringApplication.run(InvoiceApplication.class, args);
	}

	@Bean
	IntegrationFlow integrationFlow(ConsumerChannels c) {
		return IntegrationFlows
				.from(c.producer())
				.handle(Payment.class, new GenericHandler<Payment>() {
					@Override
					public Object handle(Payment payload,
										 Map<String, Object> headers) {
						paymentService.defineInvoiceByPayment(payload);
						return null;
					}}).get();
	}

}

