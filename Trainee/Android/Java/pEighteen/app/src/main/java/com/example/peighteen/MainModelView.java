package com.example.peighteen;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainModelView extends ViewModel {
    private final List<String> thoughts = new ArrayList<>();

    public void addThought(String thought) {
        thoughts.add(thought);
    }

    public List<String> getThoughts() {
        return thoughts;
    }
}
