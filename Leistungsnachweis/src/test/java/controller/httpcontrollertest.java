package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Unit tests for the Http_Controller class
 */
class Http_ControllerTest {
    private MockWebServer mockWebServer;
    private Http_Controller httpController;

    /**
     * Sets up the MockWebServer and the Http_Controller before each test
     *
     * @throws IOException if there is an error starting the MockWebServer
     */
    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        // Adjust the URL of Http_Controller to point to the MockWebServer
        httpController = new Http_Controller() {
            public String getURL() {
                return mockWebServer.url("/v1/dataset").toString();
            }
        };
    }

    /**
     * Shuts down the MockWebServer after each test.
     *
     * @throws IOException if there is an error shutting down the MockWebServer.
     */
    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    /**
     * Tests the fetchDataset method of Http_Controller to verify that
     * it correctly fetches and calculates events from the mocked server response.
     */
    @Test
    public void testFetchDataset_CalculateEvents() {
        // Simulate JSON response corresponding to the events
        String mockJsonResponse = "{\"events\": [{\"customerId\": \"403627df-88d9-4783-afee-6252c59ce20b\", \"workloadId\": \"f8413e71-cd11-4b3f-8fc5-e7ad69498eb2\", \"timestamp\": 1700290456000, \"eventType\": \"start\"}]}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(mockJsonResponse));

        // Execute the fetchDataset method
        Dataset dataset = httpController.fetchDataset();

        // Check if the dataset was returned correctly
        assertNotNull(dataset, "Dataset should not be null");

        // Expected results based on the provided data
        String expectedCustomerId = "403627df-88d9-4783-afee-6252c59ce20b";
        String expectedWorkloadId = "f8413e71-cd11-4b3f-8fc5-e7ad69498eb2";
        long expectedTimestamp = 1700290456000L;
        String expectedEventType = "start"; // Adjusted from "stop" to "start" to match the mock data

        // Verify the data in the dataset
        assertEquals(expectedCustomerId, dataset.getEvents().get(0).getCustomerId(), "The CustomerId should match");
        assertEquals(expectedWorkloadId, dataset.getEvents().get(0).getWorkloadId(), "The WorkloadId should match");
        assertEquals(expectedTimestamp, dataset.getEvents().get(0).getTimestamp(), "The timestamp should match");
        assertEquals(expectedEventType, dataset.getEvents().get(0).getEventType(), "The event type should match");
    }
}
