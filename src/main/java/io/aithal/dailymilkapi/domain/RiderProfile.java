package io.aithal.dailymilkapi.domain;

public class RiderProfile {

    private Integer riderId;
    private String name;
    private String email;
    private String address;
    private String city;
    private Integer pinCode;

    public RiderProfile ( Integer riderId, String name, String email, String address, String city, Integer pinCode ) {
        this.riderId = riderId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.city = city;
        this.pinCode = pinCode;
    }

    public Integer getRiderId ( ) {
        return riderId;
    }

    public void setRiderId ( Integer riderId ) {
        this.riderId = riderId;
    }

    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getEmail ( ) {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getAddress ( ) {
        return address;
    }

    public void setAddress ( String address ) {
        this.address = address;
    }

    public String getCity ( ) {
        return city;
    }

    public void setCity ( String city ) {
        this.city = city;
    }

    public Integer getPinCode ( ) {
        return pinCode;
    }

    public void setPinCode ( Integer pinCode ) {
        this.pinCode = pinCode;
    }
}
