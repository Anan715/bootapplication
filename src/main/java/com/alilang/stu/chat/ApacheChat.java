package com.alilang.stu.chat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ApacheChat {




    private static final String API_URL = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "";
    private static final Gson gson = new Gson();

    public static String generateText(String prompt) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();

        HttpPost request = new HttpPost(API_URL);

        request.addHeader("Content-Type", "application/json");
        request.addHeader("Authorization", "Bearer " + API_KEY);

        JsonObject json = new JsonObject();
        json.addProperty("model", "davinci");
        json.addProperty("prompt", prompt);
        json.addProperty("max_tokens", 60);
        json.addProperty("n", 1);

        StringEntity entity = new StringEntity(gson.toJson(json));
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        String responseBody = EntityUtils.toString(response.getEntity());

        JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);
        String text = jsonResponse.getAsJsonArray("choices")
                .get(0).getAsJsonObject()
                .get("text").getAsString();

        return text;
    }
}