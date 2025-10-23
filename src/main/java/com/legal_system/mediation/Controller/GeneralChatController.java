package com.legal_system.mediation.Controller;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralChatController {

    private final ChatModel chatModel;

    // This is the new system prompt for your GENERAL bot
    private final String systemPrompt = """
        You are a helpful and polite assistant for the "Resolve-IT" platform, 
        a service for legal dispute mediation in India. 
        Answer the user's questions clearly and concisely. 
        Do NOT analyze or classify cases. Your job is only to provide general information.
        """;

    @Autowired
    public GeneralChatController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @PostMapping("/chat/ask")
    public String askGeneralQuestion(@RequestParam String userQuery) {
        
        // This is the corrected approach that matches your Spring AI version

        // 1. Combine the system prompt and the user query into one string.
        String fullQuery = systemPrompt + "\n\nUser Question: " + userQuery;
        
        // 2. Call the simple .call() method, which directly returns a String
        return chatModel.call(fullQuery);
    }
}