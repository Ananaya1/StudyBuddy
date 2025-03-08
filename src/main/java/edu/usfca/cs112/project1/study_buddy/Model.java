package edu.usfca.cs112.project1.study_buddy;
import java.io.IOException;
import java.net.URI; 
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
 
public class Model {
	
	private static final Map<String,String> env = System.getenv();
   	private static final String key = env.get("OPENAI_API_KEY");


	 public String generate(String a) {
		    JSONObject payload = new JSONObject();
		    payload.put("model", "gpt-4");
		    JSONArray messages = new JSONArray();
		    JSONObject message = new JSONObject();
		    message.put("role", "user");
		    message.put("content", a);
		    messages.put(message);
		    payload.put("messages", messages);

		    HttpClient client = HttpClient.newHttpClient();
		    HttpRequest request;

		    try {
		        request = HttpRequest.newBuilder()
		                .uri(new URI("https://api.openai.com/v1/chat/completions"))
		                .header("Content-Type", "application/json")
		                .header("Authorization", "Bearer " + key)
		                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
		                .build();

		        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		        String responseBody = response.body();

		      
		        //System.out.println("Full Response: " + responseBody);

		        JSONObject jsonResponse = new JSONObject(responseBody);
		      
		        if (jsonResponse.has("choices") && jsonResponse.getJSONArray("choices").length() > 0) {
		            String generatedContent = jsonResponse.getJSONArray("choices")
		                                                  .getJSONObject(0)
		                                                  .getJSONObject("message")
		                                                  .getString("content");
		            return generatedContent;
		        } else {
		            return "Error: No choices found in the response.";
		        }

		    } catch (URISyntaxException e) {
		        System.out.println("Oops, the URL was bad.");
		    } catch (IOException e) {
		        System.out.println("Oops, the request was malformed.");
		    } catch (InterruptedException e) {
		        System.out.println("Oops, the connection was interrupted.");
		    }

		    return "Error: Unable to generate response.";
		}


	// public static void main(String[] args) {
	        // Example usage
	  //      Model myModel = new Model();
	    //    String response = myModel.generate("What is the capital of the USA?");
	      //  System.out.println(response); 
	    //}
	}

