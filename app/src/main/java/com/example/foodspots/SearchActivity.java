package com.example.foodspots;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodspots.adapters.RestaurantAdapter;
import com.example.foodspots.data.RestaurantManager;
import com.example.foodspots.models.Restaurant;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText searchEditText;
    private ChipGroup cuisineChipGroup;
    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private List<Restaurant> allRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Search Restaurants");
        }

        initializeViews();
        setupCuisineFilters();
        loadAllRestaurants();
    }

    private void initializeViews() {
        searchEditText = findViewById(R.id.search_edit_text);
        cuisineChipGroup = findViewById(R.id.cuisine_chip_group);
        recyclerView = findViewById(R.id.search_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupCuisineFilters() {
        String[] cuisines = {"Italian", "Japanese", "Mexican", "French", "Thai",
                "American", "Chinese", "Indian", "Mediterranean"};

        for (String cuisine : cuisines) {
            Chip chip = new Chip(this);
            chip.setText(cuisine);
            chip.setCheckable(true);
            chip.setChipBackgroundColorResource(R.color.chip_background);
            cuisineChipGroup.addView(chip);
        }
    }

    private void loadAllRestaurants() {
        allRestaurants = RestaurantManager.getInstance().getAllRestaurants();
        adapter = new RestaurantAdapter(this, allRestaurants);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}