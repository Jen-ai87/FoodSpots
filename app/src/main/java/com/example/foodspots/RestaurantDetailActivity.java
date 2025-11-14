package com.example.foodspots;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodspots.data.RestaurantManager;
import com.example.foodspots.models.Restaurant;
import com.google.android.material.chip.Chip;
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
        loadRestaurantData();
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

    private void loadRestaurantData() {
        int restaurantId = getIntent().getIntExtra("restaurant_id", -1);
        restaurant = RestaurantManager.getInstance().getRestaurantById(restaurantId);

        if (restaurant == null) {
            Toast.makeText(this, "Restaurant not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        int imageResource = restaurant.getImageResource();
        if (imageResource != 0) {
            imageView.setImageResource(imageResource);
        } else {
            imageView.setImageResource(R.drawable.restaurant_placeholder);
        }

        switch (restaurantId) {
            case 1: mapPreview.setImageResource(R.drawable.map1); break;
            case 2: mapPreview.setImageResource(R.drawable.map2); break;
            case 3: mapPreview.setImageResource(R.drawable.map3); break;
            case 4: mapPreview.setImageResource(R.drawable.map4); break;
            case 5: mapPreview.setImageResource(R.drawable.map5); break;
            case 6: mapPreview.setImageResource(R.drawable.map6); break;
            default: mapPreview.setImageResource(R.drawable.map1); break;
        }

        nameTextView.setText(restaurant.getName());
        addressTextView.setText(restaurant.getAddress());
        phoneTextView.setText(restaurant.getPhoneNumber());
        descriptionTextView.setText(restaurant.getDescription());
        ratingBar.setRating(restaurant.getRating());
        ratingTextView.setText(String.format("%.1f (124 reviews)", restaurant.getRating()));

        chipGroup.removeAllViews();
        for (String tag : restaurant.getTags()) {
            Chip chip = new Chip(this);
            chip.setText(tag);
            chipGroup.addView(chip);
        }

        phoneTextView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + restaurant.getPhoneNumber()));
            startActivity(intent);
        });
    }
}