package design;

import java.util.HashMap;
import java.util.Map;

public class Flyweight {
    public static void main(String[] args) {
        Color redColor = new Color("red");

        Car car = (Car) VehicleFactory.createVehicle(redColor);
        System.out.println(car);

        Car car2 = (Car) VehicleFactory.createVehicle(redColor);
        System.out.println(car2);

    }
}

class VehicleFactory {
    private static final Map<Color, Vehicle> vehicleMap = new HashMap<>();

    public static Vehicle createVehicle(Color color) {
        return vehicleMap.computeIfAbsent(color, Car::new);
    }
}

interface Vehicle {
    void start();
}

class Color {
    private String name;

    public Color(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Color{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Car implements Vehicle {
    private Color color;

    public Car(Color color) {
        this.color = color;
    }

    @Override
    public void start() {

    }
}
