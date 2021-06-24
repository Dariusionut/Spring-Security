package com.example.Spring.Security.car;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/cars")
public class CarController {

    private static final List<Car> CARS = Arrays.asList(
            new Car(1, "Tesla"),
            new Car(2, "VW"),
            new Car(3, "Audi")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_VISITOR')")
    public static List<Car> getAllCars() {
        System.out.println("getAllCars");
        return CARS;
    }

    @GetMapping("{carId}")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_VISITOR')")
    public Car getCar(@PathVariable("carId") Integer carId){
        return CARS.stream().filter(car -> carId.equals(car.getCarId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Car " + carId + " does not exist!"));

    }
}
