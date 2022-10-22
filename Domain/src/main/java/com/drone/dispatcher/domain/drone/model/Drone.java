package com.drone.dispatcher.domain.drone.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("drones")
public class Drone {

    @Id
    private String uuid;
    private String serialNumber;
    private int weightLimit;
    private int batteryCapacity;
    private Model model;
    private State state;

    public enum Model {
        LIGHT_WEIGHT, MIDDLE_WEIGHT, CRUISER_WEIGHT, HEAVY_WEIGHT
    }

    public enum State {
        IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING
    }

    public Drone markAsLoading() {
        state = State.LOADING;
        return this;
    }

    public Drone markAsLoaded() {
        state = State.LOADED;
        return this;
    }
}