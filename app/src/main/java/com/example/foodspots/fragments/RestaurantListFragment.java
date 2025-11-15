package com.example.foodspots.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
}
