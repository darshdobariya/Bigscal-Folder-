package com.example.peight.location;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.peight.R;

import java.util.ArrayList;
import java.util.List;

public class LocationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        RecyclerView rcLocation = view.findViewById(R.id.rcLocation);

        List<LocationDemo> list = new ArrayList<>();
        list.add(new LocationDemo("Bengaluru", R.drawable.banglore));
        list.add(new LocationDemo("Chennai", R.drawable.chennai));
        list.add(new LocationDemo("Delhi", R.drawable.delhi));
        list.add(new LocationDemo("Dubai", R.drawable.dubai));
        list.add(new LocationDemo("Hyderabad", R.drawable.haydrabad));
        list.add(new LocationDemo("Jammy and Kashmir", R.drawable.jammu_kashmir));
        list.add(new LocationDemo("Surat", R.drawable.surat));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        rcLocation.setLayoutManager(linearLayoutManager);

        LocationAdapter adapter = new LocationAdapter(requireActivity(), list);
        rcLocation.setAdapter(adapter);

        return view;
    }
}