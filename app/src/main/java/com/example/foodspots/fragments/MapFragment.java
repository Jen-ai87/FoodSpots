package com.example.foodspots.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.foodspots.R;
import com.example.foodspots.data.RestaurantManager;
import com.example.foodspots.models.Restaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        this.googleMap = map;

        // Add markers for all restaurants
        List<Restaurant> restaurants = RestaurantManager.getInstance().getAllRestaurants();
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();

        for (Restaurant restaurant : restaurants) {
            LatLng position = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
            googleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(restaurant.getName())
                    .snippet(String.format("Rating: %.1f stars", restaurant.getRating())));
            boundsBuilder.include(position);
        }

        // Move camera to show all markers
        if (!restaurants.isEmpty()) {
            try {
                LatLngBounds bounds = boundsBuilder.build();
                int padding = 100;
                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
            } catch (Exception e) {
                // If bounds can't be built, center on first restaurant
                LatLng firstLocation = new LatLng(
                        restaurants.get(0).getLatitude(),
                        restaurants.get(0).getLongitude()
                );
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 12f));
            }
        }

        // Enable zoom controls
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
    }
}