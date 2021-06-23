package com.example.Spring.Security.car;

public class Car {

    private final Integer carId;
    private final String carName;

    public Car(Integer carId, String carName) {
        this.carId = carId;
        this.carName = carName;
    }

    public Integer getCarId() {
        return carId;
    }

    public String getCarName() {
        return carName;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", carName='" + carName + '\'' +
                '}';
    }
}
