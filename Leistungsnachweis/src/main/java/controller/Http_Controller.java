package controller;

import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

/**
 * The Http_Controller class is responsible for fetching a dataset from a specified URL
 * It makes HTTP GET requests and converts the JSON response into a Dataset object using the Gson library
 */
public class Http_Controller {
    private static final String GET_URL = "http://localhost:8080/v1/dataset";

    /**
     * Fetches the dataset from the specified URL
     * This method sends an HTTP GET request to the server and retrieves the dataset
     * If the request is successful (HTTP status code 200), it parses the JSON response
     * into a Dataset object. If the request fails or an exception occurs, it returns null
     *
     * @return A Dataset object containing the data fetched from the server, or null if
     *         the request fails or an error occurs
     */
    public Dataset fetchDataset() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URL(GET_URL).toURI())
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String jsonResponse = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonResponse, Dataset.class);
            } else {
                System.out.println("GET request failed: " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
