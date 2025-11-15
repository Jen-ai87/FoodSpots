package com.example.foodspots.data;

import com.example.foodspots.R;
import com.example.foodspots.models.Restaurant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;models.Restaurant;
public class RestaurantManager {
    private static RestaurantManager instance;
    private List<Restaurant> restaurants;
    private int nextId = 7;

    private RestaurantManager() {
        restaurants = new ArrayList<>();
        initializeDummyData();
    }

    public static RestaurantManager getInstance() {
        if (instance == null) {
            instance = new RestaurantManager();
        }
        return instance;
    }
