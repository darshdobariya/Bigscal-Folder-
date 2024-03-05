package com.example.gfgbasics.layout.advance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.gfgbasics.MainActivity;
import com.example.gfgbasics.R;
import com.google.android.material.search.SearchBar;

import java.util.ArrayList;

public class SearchViewNormal extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view_normal);

        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listView);

        list = new ArrayList<>();

        list.add("Java");
        list.add("Android");
        list.add("JavaScript");
        list.add("Python");
        list.add("HTML");
        list.add("CSS");
        list.add("Ruby");
        list.add("C");
        list.add("C++");
        list.add("C#");
        list.add("Solidity");
        list.add("BlockChain");
        list.add(".Net");
        list.add("AWS");
        list.add("Google Collab");
        list.add("Google Cloud");
        list.add("ASP.Net");
        list.add("SQL");
        list.add("MongoDB");
        list.add("NodeJS");
        list.add("Angular");

        adapter = new ArrayAdapter<>(this, R.layout.list, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), list.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (list.contains(query)) {
                    adapter.getFilter().filter(query);
                } else {
                    Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}