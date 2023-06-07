package com.truckflow.loginReg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {

    private String name;
    private String email;
    private String Password;
    private String Role;
    private int phone;
    private String Profileimage;
    //private byte[] profileimage;
    private Date createdDate;
    private String streetNumber;
    private String streetName;
    private String unitNumber;
    private String city;
    private String province;
    private String postalCode;


}
