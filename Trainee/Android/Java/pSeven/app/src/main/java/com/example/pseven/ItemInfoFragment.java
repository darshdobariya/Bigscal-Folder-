package com.example.pseven;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemInfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item_info, container, false);

        SharedPreferences sp = requireActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);

        int imgId = sp.getInt("imageId", 0);
        String itemName = sp.getString("itemName", "");
        String itemDescription = sp.getString("itemDescription", "");

        // Use the retrieved parameters as needed
        ImageView imageView = v.findViewById(R.id.imgItem);
        imageView.setImageResource(imgId);

        TextView textViewName = v.findViewById(R.id.tvName);
        textViewName.setText(itemName);

        TextView textViewDescription = v.findViewById(R.id.tvDescription);
        textViewDescription.setText(itemDescription);

        TextView tvBack = v.findViewById(R.id.tvBack);
        tvBack.setOnClickListener(v1  ->{
            getActivity().getSupportFragmentManager().popBackStack();
        });

        return v;
    }
}