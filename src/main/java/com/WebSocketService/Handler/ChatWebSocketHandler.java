package com.WebSocketService.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import java.util.HashMap;

import java.util.Map;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Map<String,WebSocketSession> sessions=new HashMap();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String email = session.getUri().toString().split("email=")[1];
        sessions.put(email,session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,String> messageMap=objectMapper.readValue(message.getPayload(),Map.class);
        String email=messageMap.get("email");
        String messagex=messageMap.get("message");
        String target=messageMap.get("target");
        System.out.println(sessions.get(email));

        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        data.put("message", messagex);

        // convert the JSON object to a string and send it to the client-side
        String jsonData = objectMapper.writeValueAsString(data);
        sessions.get(email).sendMessage(new TextMessage(jsonData));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}
