package com.example.foodspots.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodspots.R;
import com.example.foodspots.RestaurantDetailActivity;
import com.example.foodspots.models.Restaurant;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    private List<Restaurant> restaurants;
    private Context context;

    public RestaurantAdapter(Context context, List<Restaurant> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);

        holder.nameTextView.setText(restaurant.getName());
        holder.ratingBar.setRating(restaurant.getRating());
        holder.ratingTextView.setText(String.format("%.1f", restaurant.getRating()));

        // Use restaurant image if available, otherwise use placeholder
        int imageResource = restaurant.getImageResource();
        if (imageResource != 0) {
            holder.imageView.setImageResource(imageResource);
        } else {
            holder.imageView.setImageResource(R.drawable.restaurant_placeholder);
        }

        holder.chipGroup.removeAllViews();
        for (String tag : restaurant.getTags()) {
            Chip chip = new Chip(context);
            chip.setText(tag);
            chip.setChipBackgroundColorResource(getTagColor(tag));
            chip.setTextColor(context.getResources().getColor(android.R.color.white, null));
            chip.setClickable(false);
            holder.chipGroup.addView(chip);
        }

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RestaurantDetailActivity.class);
            intent.putExtra("restaurant_id", restaurant.getId());
            context.startActivity(intent);
        });
    }

    private int getTagColor(String tag) {
        switch (tag.toLowerCase()) {
            case "italian": return R.color.tag_italian;
            case "japanese": return R.color.tag_japanese;
            case "mexican": return R.color.tag_mexican;
            case "french": return R.color.tag_french;
            case "thai": return R.color.tag_thai;
            case "american": return R.color.tag_american;
            default: return R.color.tag_default;
        }
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void updateData(List<Restaurant> newRestaurants) {
        this.restaurants = newRestaurants;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView nameTextView;
        RatingBar ratingBar;
        TextView ratingTextView;
        ChipGroup chipGroup;

        ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            imageView = itemView.findViewById(R.id.restaurant_image);
            nameTextView = itemView.findViewById(R.id.restaurant_name);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            ratingTextView = itemView.findViewById(R.id.rating_text);
            chipGroup = itemView.findViewById(R.id.chip_group);
        }
    }
}