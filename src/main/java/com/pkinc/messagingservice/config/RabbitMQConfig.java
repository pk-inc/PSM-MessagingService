package com.pkinc.messagingservice.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // Customize the RabbitTemplate if needed
        return rabbitTemplate;
    }

      @Bean
    public FanoutExchange chatExchange() {
        return new FanoutExchange("chat.exchange");
    }

    @Bean
    public Queue privateQueue() {
        return new Queue("private.queue");
    }

    @Bean
    public Binding privateBinding() {
        return BindingBuilder.bind(privateQueue()).to(chatExchange());
    }
}
