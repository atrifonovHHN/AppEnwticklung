package service;

import controller.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

/**
 * The UseTimeCalculator class calculates the total runtime for each customer based on
 * a dataset of events. It processes both start and stop events to compute the
 * total runtime associated with each customer
 */
public class UseTimeCalculator {

    /**
     * Calculates the total runtime for each customer based on the provided dataset of events
     *
     * @param dataset The dataset containing a list of events, including start and stop events
     * @return A map where the keys are customer IDs and the values are the total runtime for each customer in milliseconds
     */
    public Map<String, Long> calculateTotalRuntime(Dataset dataset) {
        Map<String, Long> customerRuntimeMap = new HashMap<>();
        Map<String, Long> workloadStartTimes = new HashMap<>();

        // Process all start events
        for (Event event : dataset.getEvents()) {
            String workloadId = event.getWorkloadId();
            String customerId = event.getCustomerId();
            long timestamp = event.getTimestamp();
            String eventType = event.getEventType();

            if (eventType.equals("start")) {
                workloadStartTimes.put(workloadId, timestamp);
            }
        }

        // Process all stop events
        for (Event event : dataset.getEvents()) {
            String workloadId = event.getWorkloadId();
            String customerId = event.getCustomerId();
            long timestamp = event.getTimestamp();
            String eventType = event.getEventType();

            if (eventType.equals("stop")) {
                // Ensure a corresponding start event exists
                if (workloadStartTimes.containsKey(workloadId)) {
                    long startTime = workloadStartTimes.get(workloadId);
                    long runtime = timestamp - startTime;
                    customerRuntimeMap.put(customerId,
                            customerRuntimeMap.getOrDefault(customerId, 0L) + runtime);
                } else {
                    System.out.println("Warning: Stop event for Workload ID: " + workloadId +
                            " without a corresponding start event.");
                }
            }
        }

        return customerRuntimeMap;
    }

    /**
     * Sends the calculated results to the specified URL
     *
     * @param results The map containing customer IDs and their total runtime
     */
    public void sendResults(Map<String, Long> results) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            Gson gson = new Gson();
            String json = gson.toJson(Map.of("result", results.entrySet().stream()
                    .map(entry -> Map.of("customerId", entry.getKey(), "consumption", entry.getValue()))
                    .toArray()));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/v1/result"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Results successfully sent.");
            } else {
                System.out.println("Failed to send results: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method that serves as the entry point for the application
     * It fetches the dataset, calculates the total runtimes for each customer, and sends the results
     */
    public static void main(String[] args) {
        System.out.println("Main method is running...");

        try {
            Thread.sleep(5000); // 5 Sekunden warten
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Http_Controller controller = new Http_Controller();
        Dataset dataset = controller.fetchDataset();

        if (dataset != null) {
            UseTimeCalculator calculator = new UseTimeCalculator();
            Map<String, Long> totalRuntimes = calculator.calculateTotalRuntime(dataset);

            for (Map.Entry<String, Long> entry : totalRuntimes.entrySet()) {
                System.out.println("Customer ID: " + entry.getKey() +
                        ", Total Runtime: " + entry.getValue() + " ms");
            }

            calculator.sendResults(totalRuntimes);
        } else {
            System.out.println("Failed to fetch dataset.");
        }
    }
}