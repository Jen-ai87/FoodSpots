package com.example.foodspots.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class User implements Serializable {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String description;
    private float rating;
    private List<String> tags;
    private double latitude;
    private double longitude;
    private int imageResource;

    public Restaurant(int id, String name, String address, String phoneNumber,
                      String description, float rating, List<String> tags,
                      double latitude, double longitude, int imageResource) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.rating = rating;
        this.tags = tags != null ? tags : new ArrayList<>();
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageResource = imageResource;
    }

