package com.example.foodspots.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodspots.AddEditRestaurantActivity;
import com.example.foodspots.R;
import com.example.foodspots.SearchActivity;
import com.example.foodspots.adapters.RestaurantAdapter;
import com.example.foodspots.data.RestaurantManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RestaurantListFragment extends Fragment {
    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private FloatingActionButton fab;
    private ImageView searchIcon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        fab = view.findViewById(R.id.fab_add);
        searchIcon = view.findViewById(R.id.search_icon);

        setupRecyclerView();
        setupFab();
        setupSearchIcon();

        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RestaurantAdapter(getContext(),
                RestaurantManager.getInstance().getAllRestaurants());
        recyclerView.setAdapter(adapter);
    }

    private void setupFab() {
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddEditRestaurantActivity.class);
            startActivity(intent);
        });
    }

    private void setupSearchIcon() {
        searchIcon.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.updateData(RestaurantManager.getInstance().getAllRestaurants());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}