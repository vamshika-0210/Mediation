package com.legal_system.mediation.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ChatbotService {

    // This RestTemplate is used to make HTTP calls to your Python service
    private final RestTemplate restTemplate = new RestTemplate();
    
    // The URL of your running Python app.py
    private final String classifierApiUrl = "http://localhost:5000/analyze";

    public String analyzeCase(String userQuery) {
        try {
            // 1. Create the JSON request body, e.g., {"message": "user query here"}
            String requestBody = "{\"message\": \"" + userQuery.replace("\"", "\\\"") + "\"}";

            // 2. Set headers to specify we are sending JSON
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(requestBody, headers);

            // 3. Call the Python API and get the response as a String
            String jsonResponse = restTemplate.postForObject(classifierApiUrl, entity, String.class);

            // 4. Parse the JSON response from Python and format it as HTML
            return formatClassifierResponse(jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Could not connect to the analysis service. Please try again later.";
        }
    }

    // This helper function turns the Python's JSON response into nice HTML
    private String formatClassifierResponse(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonResponse);

            // Extract each field from the JSON response
            String category = root.path("category").asText();
            String resolutionPath = root.path("resolution_path").asText();
            String guidance = root.path("guidance").asText();

            // Convert the "documents_needed" JSON array to a List<String>
            List<String> documents = StreamSupport.stream(
                    root.path("documents_needed").spliterator(), false)
                    .map(JsonNode::asText)
                    .collect(Collectors.toList());

            // Convert the "next_steps" JSON array to a List<String>
            List<String> nextSteps = StreamSupport.stream(
                    root.path("next_steps").spliterator(), false)
                    .map(JsonNode::asText)
                    .collect(Collectors.toList());

            // Build an HTML string to send to the browser
            StringBuilder html = new StringBuilder();
            html.append("<b>Case Analysis Results:</b><br/>");
            html.append("<b>Category:</b> ").append(category).append("<br/>");
            html.append("<b>Recommended Path:</b> ").append(resolutionPath).append("<br/>");
            html.append("<b>Guidance:</b> ").append(guidance).append("<br/>");

            html.append("<b>Required Documents:</b><ul>");
            for (String doc : documents) {
                html.append("<li>").append(doc).append("</li>");
            }
            html.append("</ul>");

            html.append("<b>Next Steps:</b><ul>");
            for (String step : nextSteps) {
                html.append("<li>").append(step).append("</li>");
            }
            html.append("</ul>");

            return html.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Could not parse the analysis response.";
        }
    }
}