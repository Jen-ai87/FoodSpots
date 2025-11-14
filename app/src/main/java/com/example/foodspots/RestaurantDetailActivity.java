package com.example.foodspots;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodspots.models.Restaurant;
import com.google.android.material.chip.ChipGroup;

public class RestaurantDetailActivity extends AppCompatActivity {
    private Restaurant restaurant;
    private ImageView imageView;
    private ImageView mapPreview;
    private TextView nameTextView, addressTextView, phoneTextView, descriptionTextView, ratingTextView;
    private RatingBar ratingBar;
    private ChipGroup chipGroup;
    private Button btnViewMap, btnDirections, btnShare, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Restaurant Details");
        }

        initializeViews();
    }

    private void initializeViews() {
        imageView = findViewById(R.id.restaurant_image);
        mapPreview = findViewById(R.id.map_preview);
        nameTextView = findViewById(R.id.restaurant_name);
        addressTextView = findViewById(R.id.address_text);
        phoneTextView = findViewById(R.id.phone_text);
        descriptionTextView = findViewById(R.id.description_text);
        ratingBar = findViewById(R.id.rating_bar);
        ratingTextView = findViewById(R.id.rating_text);
        chipGroup = findViewById(R.id.chip_group);
        btnViewMap = findViewById(R.id.btn_view_map);
        btnDirections = findViewById(R.id.btn_directions);
        btnShare = findViewById(R.id.btn_share);
        btnEdit = findViewById(R.id.btn_edit);
    }
}