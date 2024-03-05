package com.example.gfgbasics.database.firebase.firestore.introslider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.gfgbasics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SliderAdapter extends PagerAdapter {

    // creating variables for layout inflater,
    // context and array list.
    LayoutInflater layoutInflater;
    Context context;
    ArrayList<SliderModel> sliderModalArrayList;

    // creating constructor.
    public SliderAdapter(Context context, ArrayList<SliderModel> sliderModalArrayList) {
        this.context = context;
        this.sliderModalArrayList = sliderModalArrayList;
    }

    @Override
    public int getCount() {
        // inside get count method returning
        // the size of our array list.
        return sliderModalArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        // inside isViewFromobject method we are
        // returning our Relative layout object.
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // in this method we will initialize all our layout items
        // and inflate our layout file as well.
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // below line is use to inflate the layout file which we created.
        View view = layoutInflater.inflate(R.layout.image_intro_slider_item, container, false);

        // initializing our views.
        ImageView imageView = view.findViewById(R.id.idIV);
        TextView titleTV = view.findViewById(R.id.idTVtitle);
        TextView headingTV = view.findViewById(R.id.idTVheading);

        // setting data to our views.
        SliderModel modal = sliderModalArrayList.get(position);
        titleTV.setText(modal.getTitle());
        headingTV.setText(modal.getHeading());
        Picasso.get().load(modal.getImgUrl()).into(imageView);

        // after setting the data to our views we
        // are adding the view to our container.
        container.addView(view);

        // at last we are returning the view.
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // this is a destroy view method which
        // is use to remove a view.
        container.removeView((RelativeLayout) object);
    }
}

