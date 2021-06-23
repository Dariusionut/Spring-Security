package com.example.Spring.Security.car;

import com.example.Spring.Security.client.Client;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/cars")
public class CarManagementController {

    private static final List<Car> CARS = Arrays.asList(
            new Car(1, "Tesla"),
            new Car(2, "VW"),
            new Car(3, "Audi")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public static List<Car> getAllCars() {
        System.out.println("getAllCars");
        return CARS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('car:write')")
    public void registerNewCar(@RequestBody Car car){
        System.out.println("registerNewCar");
        System.out.println(car);
    }

    @DeleteMapping(path = "{carId}")
    @PreAuthorize("hasAuthority('car:write')")
    public void deleteCar(@PathVariable("carId") Integer carId) {
        System.out.println("deleteCar");
        System.out.println(carId);
}

    @PutMapping(path = "{carId}")
    @PreAuthorize("hasAuthority('car:write')")
    public void updateCar(@PathVariable("carId") Integer carId, @RequestBody Car car) {
        System.out.println("updateCar");
        System.out.println(String.format("%s %s", carId, car));
    }
}
