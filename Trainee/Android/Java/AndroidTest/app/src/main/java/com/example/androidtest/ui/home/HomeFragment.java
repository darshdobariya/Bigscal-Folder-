package com.example.androidtest.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.androidtest.R;
import com.example.androidtest.databinding.FragmentHomeBinding;
import com.example.androidtest.homefragment.EventFragment;
import com.example.androidtest.homefragment.ProfileFragment;
import com.example.androidtest.homefragment.SettingFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    CardView cvProfile, cvSetting, cvEvent;
    TextView tvProfile, tvSetting,tvEvent;
    ImageView imgProfile, imgSetting, imgEvent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cvProfile = root.findViewById(R.id.cvProfile);
        cvSetting = root.findViewById(R.id.cvSetting);
        cvEvent = root.findViewById(R.id.cvEvent);
        tvProfile = root.findViewById(R.id.tvProfile);
        tvSetting = root.findViewById(R.id.tvSetting);
        tvEvent = root.findViewById(R.id.tvEvent);
        imgProfile = root.findViewById(R.id.imgProfile);
        imgSetting = root.findViewById(R.id.imgSetting);
        imgEvent = root.findViewById(R.id.imgEvent);

        selectFragment(new EventFragment(), imgEvent, tvEvent, cvEvent);

        cvEvent.setOnClickListener(v-> selectFragment(new EventFragment(), imgEvent, tvEvent, cvEvent));

        cvProfile.setOnClickListener(v-> selectFragment(new ProfileFragment(), imgProfile, tvProfile, cvProfile));

        cvSetting.setOnClickListener(v-> selectFragment(new SettingFragment(), imgSetting, tvSetting, cvSetting));

        return root;
    }

    @SuppressLint("ResourceAsColor")
    private void selectFragment(Fragment fragment, ImageView imgView, TextView textView, CardView cardView){
        if (fragment != null) {

            resetNavigationBar();

            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frmMain, fragment)
                    .commit();

            // set image color of selected fragment icon
            imgView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);

            // set font style of selected fragment text
            Typeface typeface = ResourcesCompat.getFont(requireContext(), R.font.roboto_bold);
            textView.setTypeface(typeface);
            textView.setTextColor(Color.WHITE);

            // set card background tint
            cardView.setCardBackgroundColor(R.color.purple_background);
        }
    }

    @SuppressLint("ResourceAsColor")
    private void resetNavigationBar(){
        // set image color of selected fragment icon
        imgEvent.setColorFilter(ContextCompat.getColor(requireContext(), R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
        imgProfile.setColorFilter(ContextCompat.getColor(requireContext(), R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
        imgSetting.setColorFilter(ContextCompat.getColor(requireContext(), R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);

        // set font style of selected fragment text
        Typeface typeface = ResourcesCompat.getFont(requireContext(), R.font.roboto_regular);
        tvEvent.setTypeface(typeface);
        tvEvent.setTextColor(Color.BLACK);

        tvProfile.setTypeface(typeface);
        tvProfile.setTextColor(Color.BLACK);

        tvSetting.setTypeface(typeface);
        tvSetting.setTextColor(Color.BLACK);

        // set card background tint
        cvEvent.setCardBackgroundColor(Color.TRANSPARENT);
        cvProfile.setCardBackgroundColor(Color.TRANSPARENT);
        cvSetting.setCardBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}