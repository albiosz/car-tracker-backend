package com.example.cartracker.vehicle;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="vehicles")
public class VehicleControler {
  
  private final VehicleService vehicleService;

  public VehicleControler(VehicleService vehicleService) {
    this.vehicleService = vehicleService;
  }

  @GetMapping
  public List<Vehicle> getVehicles() {
    return vehicleService.getVehicles();
  }


}
