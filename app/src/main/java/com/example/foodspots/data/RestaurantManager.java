package com.example.foodspots.data;

import com.example.foodspots.R;
import com.example.foodspots.models.Restaurant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private void initializeDummyData() {
        restaurants.add(new Restaurant(1, "Bella Vista Trattoria & Wine Bar",
                "660 College St, Toronto, ON M6G 1B8",
                "(416) 532-2518",
                "An absolutely wonderful dining experience!",
                4.8f,
                Arrays.asList("Italian", "Fine Dining"),
                40.7580, -73.9855, R.drawable.restaurant1));

        restaurants.add(new Restaurant(2, "Sakura Sushi House",
                "1550 Kingston Rd #3, Pickering, ON L1V 1X6",
                "(905) 420-9071",
                "Authentic Japanese cuisine",
                4.2f,
                Arrays.asList("Japanese", "Sushi"),
                40.7549, -73.9840, R.drawable.restaurant2));

        restaurants.add(new Restaurant(3, "Wilbur Mexicana",
                "552 King St W, Toronto, ON M5V 1M3",
                "(416) 792-1878",
                "Vibrant Mexican restaurant",
                4.6f,
                Arrays.asList("Mexican", "Casual"),
                40.7295, -73.9965, R.drawable.restaurant3));

        restaurants.add(new Restaurant(4, "Le Sélect Bistro",
                "432 Wellington St W, Toronto, ON M5V 1E3",
                "(416) 626-6262",
                "Charming French bistro",
                4.3f,
                Arrays.asList("French", "Bistro"),
                40.7831, -73.9712, R.drawable.restaurant4));

        restaurants.add(new Restaurant(5, "Burger Hill",
                "2326 Danforth Ave, East York, ON M4C 1K7",
                "(416) 421-8989",
                "Best burgers in town!",
                4.1f,
                Arrays.asList("American", "Fast Food"),
                40.6892, -73.9900, R.drawable.restaurant5));

        restaurants.add(new Restaurant(6, "Garden Gate Restaurant\n",
                "2379 Queen St E, Toronto, ON M4E 3K2",
                "(416) 694-9696",
                "Authentic Thai cuisine",
                4.7f,
                Arrays.asList("Thai", "Authentic"),
                40.7505, -73.9310, R.drawable.restaurant6));
    }

    public List<Restaurant> getAllRestaurants() {
        return new ArrayList<>(restaurants);
    }

    public Restaurant getRestaurantById(int id) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == id) {
                return restaurant;
            }
        }
        return null;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurant.setId(nextId++);
        restaurants.add(restaurant);
    }

    public void updateRestaurant(Restaurant updatedRestaurant) {
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getId() == updatedRestaurant.getId()) {
                restaurants.set(i, updatedRestaurant);
                break;
            }
        }
    }

    public void deleteRestaurant(int id) {
        restaurants.removeIf(restaurant -> restaurant.getId() == id);
    }

    public List<Restaurant> searchRestaurants(String query) {
        List<Restaurant> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().toLowerCase().contains(lowerQuery)) {
                results.add(restaurant);
            }
        }
        return results;
    }

    public List<Restaurant> filterByTag(String tag) {
        List<Restaurant> results = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getTags().contains(tag)) {
                results.add(restaurant);
            }
        }
        return results;
    }
}