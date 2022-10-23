package com.drone.dispatcher.drone.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Arrays;
import java.util.Objects;

@Data
@Builder
@Table("drones")
public class Drone {

    @Id
    private Long id;
    private String uuid;
    private String serialNumber;
    private int weightLimit;
    private int batteryCapacity;
    private Model model;
    private State state;

    public enum Model {
        LIGHT_WEIGHT, MIDDLE_WEIGHT, CRUISER_WEIGHT, HEAVY_WEIGHT;

        public static boolean isValidModel(String model) {
            return Arrays.stream(values()).map(Objects::toString).anyMatch(model::equals);
        }
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
