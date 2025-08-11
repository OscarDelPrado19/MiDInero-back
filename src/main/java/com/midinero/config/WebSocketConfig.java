package com.midinero.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Destinos que atiende el broker en memoria (suscripciones)
        config.enableSimpleBroker("/queue", "/topic");
        // Prefijo para endpoints @MessageMapping del lado servidor
        config.setApplicationDestinationPrefixes("/app");
        // Prefijo lógico para destinos por-usuario (/user/queue/...)
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Debe coincidir con tu cliente (https://midinero-back.onrender.com/ws)
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}