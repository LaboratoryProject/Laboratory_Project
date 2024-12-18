package com.laboratoire.service_ai.service;

import com.laboratoire.service_ai.model.ChatMessage;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AIChatService {
    private final ChatClient chatClient;

    // In-memory storage for the conversation history (shared for the session)
    private final List<ChatMessage> conversationHistory = new ArrayList<>();

    @Autowired
    public AIChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generateContextualResponse(String userMessage) {
        // Log the user message
        System.out.println("Received userMessage: " + userMessage);

        if (userMessage == null || userMessage.trim().isEmpty()) {
            System.out.println("Invalid userMessage received.");
            return "Please provide a valid message.";
        }

        // Build the conversation context
        StringBuilder contextBuilder = new StringBuilder();
        for (ChatMessage message : conversationHistory) {
            contextBuilder.append(message.getType()) // Role: User or AI
                    .append(": ")
                    .append(message.getContent())
                    .append("\n");
        }
        contextBuilder.append("User: ").append(userMessage);

        System.out.println("Context Sent to AI: " + contextBuilder.toString());

        try {
            String aiResponse = chatClient.prompt()
                    .user(contextBuilder.toString())
                    .call()
                    .content();

            System.out.println("AI Response: " + aiResponse);

            // Update the conversation history
            conversationHistory.add(new ChatMessage("User", userMessage));
            conversationHistory.add(new ChatMessage("AI", aiResponse));

            // Limit history size
            if (conversationHistory.size() > 20) {
                conversationHistory.subList(0, conversationHistory.size() - 20).clear();
            }

            return aiResponse;
        } catch (Exception e) {
            System.err.println("Error generating AI response: " + e.getMessage());
            return "I'm sorry, but I couldn't generate a contextual response right now.";
        }
    }

}
