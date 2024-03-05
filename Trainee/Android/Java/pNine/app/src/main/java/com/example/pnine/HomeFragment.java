package com.example.pnine;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    private EditText edtUrl;
    private Button btnGo;
    private SharedViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_h_ome, container, false);

        edtUrl = view.findViewById(R.id.edtUrl);
        btnGo = view.findViewById(R.id.btnGo);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Restore URL from ViewModel
        viewModel.getUrl().observe(getViewLifecycleOwner(), url -> {
            if (url != null) {
                edtUrl.setText(url);
            }
        });

        // Restore URL from savedInstanceState
        if (savedInstanceState != null) {
            String savedUrl = savedInstanceState.getString("url");
            if (savedUrl != null) {
                edtUrl.setText(savedUrl);
            }
        }

        edtUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // Save URL to ViewModel
//                viewModel.setUrl(edtUrl.getText().toString());
            }
        });

        btnGo.setOnClickListener(v -> {
            if (checkUrl(edtUrl.getText().toString())) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.frmLayout, new BrowserFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private boolean checkUrl(String url) {
        if (URLUtil.isValidUrl(url)) {
            return true;
        } else {
            Toast.makeText(requireActivity(), "URL not valid", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save URL to savedInstanceState
        outState.putString("url", edtUrl.getText().toString());
    }
}
