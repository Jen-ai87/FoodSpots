package com.example.foodspots;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodspots.data.RestaurantManager;
import com.example.foodspots.models.Restaurant;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class AddEditRestaurantActivity extends AppCompatActivity {
    private EditText nameEditText, addressEditText, phoneEditText, descriptionEditText;
    private RatingBar ratingBar;
    private ChipGroup tagChipGroup;
    private Button saveButton, cancelButton;
    private Restaurant editingRestaurant;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_restaurant);

        initializeViews();
        setupPopularTags();
        checkEditMode();
    }

    private void initializeViews() {
        nameEditText = findViewById(R.id.edit_name);
        addressEditText = findViewById(R.id.edit_address);
        phoneEditText = findViewById(R.id.edit_phone);
        descriptionEditText = findViewById(R.id.edit_description);
        ratingBar = findViewById(R.id.rating_bar);
        tagChipGroup = findViewById(R.id.tag_chip_group);
        saveButton = findViewById(R.id.btn_save);
        cancelButton = findViewById(R.id.btn_cancel);
    }

    private void setupPopularTags() {
        String[] popularTags = {"Italian", "Japanese", "Mexican", "French", "Thai",
                "American", "Chinese", "Indian", "Vegetarian", "Vegan"};

        for (String tag : popularTags) {
            Chip chip = new Chip(this);
            chip.setText(tag);
            chip.setCheckable(true);
            chip.setChipBackgroundColorResource(R.color.chip_background);
            tagChipGroup.addView(chip);
        }
    }

    private void checkEditMode() {
        int restaurantId = getIntent().getIntExtra("restaurant_id", -1);
        if (restaurantId != -1) {
            isEditMode = true;
            editingRestaurant = RestaurantManager.getInstance().getRestaurantById(restaurantId);
            if (editingRestaurant != null) {
                populateFields();
                setTitle("Edit Restaurant");
            }
        } else {
            setTitle("Add Restaurant");
        }
    }

    private void populateFields() {
        nameEditText.setText(editingRestaurant.getName());
        addressEditText.setText(editingRestaurant.getAddress());
        phoneEditText.setText(editingRestaurant.getPhoneNumber());
        descriptionEditText.setText(editingRestaurant.getDescription());
        ratingBar.setRating(editingRestaurant.getRating());

        for (String tag : editingRestaurant.getTags()) {
            for (int i = 0; i < tagChipGroup.getChildCount(); i++) {
                Chip chip = (Chip) tagChipGroup.getChildAt(i);
                if (chip.getText().toString().equals(tag)) {
                    chip.setChecked(true);
                    break;
                }
            }
        }
    }
}