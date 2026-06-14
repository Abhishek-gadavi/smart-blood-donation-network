package com.blood.blooddonation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "donor")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Column(name = "blood_group")
    private String bloodGroup;
    
    private String phone;
    private String location;
    private int age;

    // GPS Spatial Coordinates Fields
    private Double latitude;
    private Double longitude;

    // Constructors
    public Donor() {}

    public Donor(String name, String bloodGroup, String phone, String location, int age, Double latitude, Double longitude) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.phone = phone;
        this.location = location;
        this.age = age;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
}