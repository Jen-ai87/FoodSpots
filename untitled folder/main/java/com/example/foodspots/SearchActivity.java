package com.example.foodspots;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodspots.R;
import com.example.foodspots.adapters.RestaurantAdapter;
import com.example.foodspots.data.RestaurantManager;
import com.example.foodspots.models.Restaurant;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText searchEditText;
    private ChipGroup cuisineChipGroup;
    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private List<Restaurant> allRestaurants;
    private String currentSearchQuery = "";
    private String currentFilterTag = "";

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
        setupSearch();
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
            chip.setOnClickListener(v -> {
                // Uncheck all other chips
                for (int i = 0; i < cuisineChipGroup.getChildCount(); i++) {
                    Chip c = (Chip) cuisineChipGroup.getChildAt(i);
                    if (c != chip) {
                        c.setChecked(false);
                    }
                }

                if (chip.isChecked()) {
                    currentFilterTag = cuisine;
                } else {
                    currentFilterTag = "";
                }
                performSearch();
            });
            cuisineChipGroup.addView(chip);
        }
    }

    private void setupSearch() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentSearchQuery = s.toString();
                performSearch();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void loadAllRestaurants() {
        allRestaurants = RestaurantManager.getInstance().getAllRestaurants();
        adapter = new RestaurantAdapter(this, allRestaurants);
        recyclerView.setAdapter(adapter);
    }

    private void performSearch() {
        List<Restaurant> filteredList = new ArrayList<>();

        for (Restaurant restaurant : allRestaurants) {
            boolean matchesSearch = currentSearchQuery.isEmpty() ||
                    restaurant.getName().toLowerCase().contains(currentSearchQuery.toLowerCase());

            boolean matchesFilter = currentFilterTag.isEmpty() ||
                    restaurant.getTags().contains(currentFilterTag);

            if (matchesSearch && matchesFilter) {
                filteredList.add(restaurant);
            }
        }

        adapter.updateData(filteredList);
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