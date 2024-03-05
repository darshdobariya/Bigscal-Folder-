package com.example.gfgbasics.layout.viewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.gfgbasics.R;
import com.example.gfgbasics.layout.demo.GDDemo;

import java.util.ArrayList;

public class GDAdapter extends ArrayAdapter<GDDemo> {
    public GDAdapter(@NonNull Context context, ArrayList<GDDemo> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.gridview, parent, false);
        }

        GDDemo courseModel = getItem(position);
        TextView cName = listitemView.findViewById(R.id.courseName);
        TextView cRating = listitemView.findViewById(R.id.courseRating);
        ImageView cIndicator = listitemView.findViewById(R.id.courseIndicator);

        cName.setText(courseModel.getCourseName());
        cRating.setText(String.valueOf(courseModel.getRate()));

        if(courseModel.getRate() > 0){
            cIndicator.setColorFilter(ContextCompat.getColor(getContext(), R.color.RED), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if(courseModel.getRate() > 2){
            cIndicator.setColorFilter(ContextCompat.getColor(getContext(), R.color.YELLOW), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if(courseModel.getRate() > 3){
            cIndicator.setColorFilter(ContextCompat.getColor(getContext(), R.color.GREEN), android.graphics.PorterDuff.Mode.SRC_IN);
        }

        return listitemView;
    }
}