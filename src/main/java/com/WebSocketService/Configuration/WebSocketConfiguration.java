package com.WebSocketService.Configuration;

import com.WebSocketService.Handler.ChatWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    private static final String CHAT_ENDPOINT = "/chat";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry handler) {
        handler.addHandler(getChatWebSocketHandler(), CHAT_ENDPOINT).setAllowedOrigins();
    }

    @Bean
    public WebSocketHandler getChatWebSocketHandler() {
        return new ChatWebSocketHandler();
    }
}
