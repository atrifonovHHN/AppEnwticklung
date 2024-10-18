package controller;

import java.util.List;

/**
 * The Dataset class represents a collection of events.
 * It contains a list of Event objects, each of which holds information about a specific event associated with a customer and workload
 */
public class Dataset {

    private List<Event> events;

    /**
     * Returns the list of events in the dataset
     *
     * @return A list of Event objects
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Sets the list of events in the dataset
     *
     * @param events A list of Event objects to set
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * Returns a string representation of the Dataset object
     *
     * @return A string that includes the list of events in the dataset
     */
    @Override
    public String toString() {
        return "Dataset{" +
                "events=" + events +
                '}';
    }
}
