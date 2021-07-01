package io.aithal.dailymilkapi.domain;

public class Rider {

    private Integer riderId;
    private String name;
    private Long phone;
    private String password;

    public Rider ( Integer riderId, String name, Long phone, String password ) {
        this.riderId = riderId;
        this.name = name;
        this.phone = phone;
        this.password = password;
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

    public Long getPhone ( ) {
        return phone;
    }

    public void setPhone ( Long phone ) {
        this.phone = phone;
    }

    public String getPassword ( ) {
        return password;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }
}
