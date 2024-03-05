package com.example.peight;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchFragment extends Fragment{

    private ArrayAdapter<String> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        SearchView searchView = view.findViewById(R.id.searchView);
        ListView listView = view.findViewById(R.id.listView);
        TextView tvError = view.findViewById(R.id.tvError);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Bangalore");
        arrayList.add("Chennai");
        arrayList.add("Delhi");
        arrayList.add("Dubai");
        arrayList.add("Hyderabad");
        arrayList.add("Jammu Kashmir");
        arrayList.add("Kolkata");
        arrayList.add("Vapi");
        arrayList.add("Surat");
        arrayList.add("Mumbai");
        arrayList.add("Pune");
        arrayList.add("Ranchi");

        adapter=new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                if (listView.getCount() == 0) {
                    tvError.setVisibility(View.VISIBLE);
                }else {
                    tvError.setVisibility(View.GONE);
                }

                return false;
            }
        });

        return view;
    }
}