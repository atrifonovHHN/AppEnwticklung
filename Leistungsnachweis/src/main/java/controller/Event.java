package controller;

/**
 * The Event class represents an event associated with a customer and a workload
 * It contains details such as the customer ID, workload ID, timestamp of the event,
 * and the type of event (e.g., start or stop)
 */
public class Event {

    private String customerId;
    private String workloadId;
    private long timestamp;
    private String eventType;

    /**
     * Returns the customer ID associated with the event
     *
     * @return A string representing the customer ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID for the event
     *
     * @param customerId A string representing the customer ID to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Returns the workload ID associated with the event
     *
     * @return A string representing the workload ID
     */
    public String getWorkloadId() {
        return workloadId;
    }

    /**
     * Sets the workload ID for the event
     *
     * @param workloadId A string representing the workload ID to set
     */
    public void setWorkloadId(String workloadId) {
        this.workloadId = workloadId;
    }

    /**
     * Returns the timestamp of the event
     *
     * @return A long representing the timestamp in milliseconds
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp for the event
     *
     * @param timestamp A long representing the timestamp in milliseconds to set
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the type of the event (e.g., start or stop)
     *
     * @return A string representing the event type
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Sets the type of the event
     *
     * @param eventType A string representing the event type to set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * Returns a string representation of the Event object
     *
     * @return A string that includes the customer ID, workload ID, timestamp, and event type.
     */
    @Override
    public String toString() {
        return "Event{" +
                "customerId='" + customerId + '\'' +
                ", workloadId='" + workloadId + '\'' +
                ", timestamp=" + timestamp +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}
