package io.aithal.dailymilkapi.domain;

public class UserProfile {
    private Integer userId;
    private String name;
    private String address;
    private String city;
    private Integer pinCode;

    public UserProfile ( Integer userId, String name, String address, String city, Integer pinCode ) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.pinCode = pinCode;
    }

    public Integer getUserId ( ) {
        return userId;
    }

    public void setUserId ( Integer userId ) {
        this.userId = userId;
    }

    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
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
