package com.example.foodspots;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodspots.data.RestaurantManager;
import com.example.foodspots.models.Restaurant;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.List;

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
        setupButtons();
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

    private void setupButtons() {
        saveButton.setOnClickListener(v -> saveRestaurant());
        cancelButton.setOnClickListener(v -> finish());
    }

    private void saveRestaurant() {
        String name = nameEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        float rating = ratingBar.getRating();

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter restaurant name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (address.isEmpty()) {
            Toast.makeText(this, "Please enter address", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> selectedTags = new ArrayList<>();
        for (int i = 0; i < tagChipGroup.getChildCount(); i++) {
            Chip chip = (Chip) tagChipGroup.getChildAt(i);
            if (chip.isChecked()) {
                selectedTags.add(chip.getText().toString());
            }
        }

        if (isEditMode) {
            editingRestaurant.setName(name);
            editingRestaurant.setAddress(address);
            editingRestaurant.setPhoneNumber(phone);
            editingRestaurant.setDescription(description);
            editingRestaurant.setRating(rating);
            editingRestaurant.setTags(selectedTags);
            RestaurantManager.getInstance().updateRestaurant(editingRestaurant);
            Toast.makeText(this, "Restaurant updated", Toast.LENGTH_SHORT).show();
        } else {
            Restaurant newRestaurant = new Restaurant(0, name, address, phone, description,
                    rating, selectedTags, 40.7580, -73.9855, 0);
            RestaurantManager.getInstance().addRestaurant(newRestaurant);
            Toast.makeText(this, "Restaurant added", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}