//package com.alilang.stu.chat;
//
//import org.springframework.stereotype.Service;
//
//import javax.annotation.processing.Completions;
//
//import ai.openai.api.models.CompletionRequest;
//        import ai.openai.api.models.CompletionResponse;
//        import ai.openai.api.models.Model;
//        import ai.openai.api.models.Versions;
//        import ai.openai.api.requests.Completions;
//
//        import org.springframework.beans.factory.annotation.Value;
//        import org.springframework.stereotype.Service;
//
//@Service
//public class ChatGPTService {
//
//    @Value("${openai.apiKey}")
//    private String apiKey;
//
//    @Value("${openai.modelId}")
//    private String modelId;
//
//    public String generateResponse(String prompt) {
//        Completions completions = new Completions(apiKey);
//        CompletionRequest request = new CompletionRequest();
//        request.setModel(new Model(modelId));
//        request.setPrompt(prompt);
//        request.setMaxTokens(60);
//        request.setN(1);
//        CompletionResponse response = completions.createCompletion(request);
//        return response.getChoices().get(0).getText();
//    }
//}