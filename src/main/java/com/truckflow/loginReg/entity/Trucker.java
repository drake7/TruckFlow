package com.truckflow.loginReg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trucker {

    private String vid; // primary key
    private String uid; // foreign key referencing the Users table
    private String VIN;
    private String make;
    private String model;
    private int mileage;
    private int year;
    private String vehicleType;
    private String fuelType;
    private String insuranceInfo;
    private String vehicleColor;
    private String vehicleImages;
    private int vehicleCapacity;
    private double pricePerDay;

}
