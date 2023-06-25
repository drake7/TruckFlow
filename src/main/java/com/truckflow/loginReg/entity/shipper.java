package com.truckflow.loginReg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class shipper {

    private String shipper_id; // primary key
    private String user_id;  //foreign key
    private String company_name;
    private String contact_person;
    private String email;
    private String phone_number;
    private String load_address;
    private String load_weight;
    private String load_destination;
    private String pickup_date;
    private String pickup_time;
    private String delivery_date;
    private String delivery_time;

}
