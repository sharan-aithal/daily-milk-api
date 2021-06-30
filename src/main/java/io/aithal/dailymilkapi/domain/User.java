package io.aithal.dailymilkapi.domain;

public class User {

    private Integer userid;
    private String name;
    private String email;
    private Long phone;
    private String password;

    public User ( Integer userid, String name, String email, Long phone, String password ) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Integer getUserid ( ) {
        return userid;
    }

    public void setUserid ( Integer userid ) {
        this.userid = userid;
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
