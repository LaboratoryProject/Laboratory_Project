package com.laboratoire.service_ai.controller;

import com.laboratoire.service_ai.service.AIChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:4200",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class ChatRestController {

    @Autowired
    private AIChatService aiChatService;

    // DTO class to map incoming JSON payload
    public static class ChatRequest {
        public String userMessage;
    }

    @PostMapping("/ask")
    public String sendMessage(@RequestBody ChatRequest request) {
        if (request.userMessage == null || request.userMessage.trim().isEmpty()) {
            return "Please provide a valid message.";
        }
        return aiChatService.generateContextualResponse(request.userMessage);
    }
}
