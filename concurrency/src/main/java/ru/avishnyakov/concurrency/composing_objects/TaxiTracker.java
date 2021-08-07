package ru.avishnyakov.concurrency.composing_objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaxiTracker {
    List<Vehicle> vehicles = new ArrayList<>();

    public Map<String, Point> getLocations() {
        return vehicles.stream()
                .collect(Collectors.toMap(Vehicle::getVehicleId, Vehicle::getLocations));
    }

    public void setLocation(String vehicleId, int x, int y) {
        vehicles.stream()
                .filter(v -> v.getVehicleId().equals(vehicleId))
                .findFirst()
                .ifPresent(v -> {
                    Point locations = v.getLocations();
                    locations.setX(x);
                    locations.setY(y);
                });
    }

    public static void main(String[] args) {
        TaxiTracker vehicles = new TaxiTracker();
        Map<String, Point> locations = vehicles.getLocations();
        for (String key : locations.keySet()) {
            vehicles.renderVehicle(key, locations.get(key));
        }
    }

    public void renderVehicle(String key, Point point) {
    }

    public void vehicleMoved(VehicleMovedEvent evt) {
        Point loc = evt.getNewLocation();
        setLocation(evt.getVehicleId(), loc.x, loc.y);
    }
}


class VehicleMovedEvent {
    public Point getNewLocation() {
        return null;
    }

    public String getVehicleId() {
        return null;
    }
}

class Vehicle {
    String vehicleId;
    Point locations;

    public Vehicle(String vehicleId, Point locations) {
        this.vehicleId = vehicleId;
        this.locations = locations;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public Point getLocations() {
        return locations;
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}