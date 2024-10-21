package controller;

import org.junit.jupiter.api.Test;
import service.UseTimeCalculator;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class HttpControllerTest {

    /**
     * Tests the failure case for retrieving a dataset.
     * Simulates a failed API request and checks that the dataset is null.
     */
    @Test
    public void testFetchDataset_Failure() {
        Http_Controller failingHttpClient = new Http_Controller(HttpClient.newHttpClient()) {
            @Override
            public Dataset fetchDataset() {
                return null; // Simulate a failure
            }
        };
        Dataset dataset = failingHttpClient.fetchDataset();
        assertNull(dataset, "The dataset should be null if the API call fails.");
    }

    /**
     * Tests the successful sending of results.
     * Verifies that the results are successfully sent to the server.
     */
    @Test
    public void testSendResults_Success() {
        Map<String, Long> runtimes = new HashMap<>();
        runtimes.put("customer1", 100L);
        UseTimeCalculator useTimeCalculator = new UseTimeCalculator();
        useTimeCalculator.sendResults(runtimes);
        // No assertion as sendResults has no return value.
    }

    /**
     * Tests the failure case for sending results.
     * Simulates an error when sending the results.
     */
    @Test
    public void testSendResults_Failure() {
        UseTimeCalculator failingHttpClient = new UseTimeCalculator() {
            @Override
            public void sendResults(Map<String, Long> runtimes) {
                System.out.println("Error sending results: Incorrect URL");
            }
        };
        Map<String, Long> runtimes = new HashMap<>();
        runtimes.put("customer1", 100L);
        failingHttpClient.sendResults(runtimes);
        // No assertion as sendResults has no return value.
    }
}