package com.alilang.stu.chat;

import com.github.plexpt.chatgpt.Chatbot;

import java.util.Map;

public class ChatDemo {

    public void testMethod() {

        Chatbot chatbot = new Chatbot("sessionToken", "cf_clearance", "user-agent");
        chatbot.setHost("http://proxyaddr:5000");

        Map<String, Object> chatResponse = chatbot.getChatResponse("hello");
        System.out.println(chatResponse.get("message"));
    }
}
