package com.example.gfgbasics.advanceview.diprecycleview.nested;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gfgbasics.R;
import com.example.gfgbasics.advanceview.diprecycleview.nested.adapter.AdapterParent;
import com.example.gfgbasics.advanceview.diprecycleview.nested.demo.DemoChild;
import com.example.gfgbasics.advanceview.diprecycleview.nested.demo.DemoParent;

import java.util.ArrayList;
import java.util.List;

public class RCNestedMain extends AppCompatActivity {

    RecyclerView rcView;
    AdapterParent adapterParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcnested_main);

        rcView = findViewById(R.id.recycleView);

        List<DemoChild> child = new ArrayList<>();
        child.add(new DemoChild(R.color.color1, "Color 1"));
        child.add(new DemoChild(R.color.color2, "Color 2"));
        child.add(new DemoChild(R.color.color3, "Color 3"));
        child.add(new DemoChild(R.color.color4, "Color 4"));
        child.add(new DemoChild(R.color.color5, "Color 5"));

        List<DemoChild> child1 = new ArrayList<>();
        child1.add(new DemoChild(R.color.color6, "Color 6"));
        child1.add(new DemoChild(R.color.color7, "Color 7"));
        child1.add(new DemoChild(R.color.color8, "Color 8"));
        child1.add(new DemoChild(R.color.color9, "Color 9"));
        child1.add(new DemoChild(R.color.color10, "Color 10"));

        List<DemoChild> child2 = new ArrayList<>();
        child2.add(new DemoChild(R.color.color11, "Color 11"));
        child2.add(new DemoChild(R.color.color12, "Color 12"));
        child2.add(new DemoChild(R.color.color13, "Color 13"));
        child2.add(new DemoChild(R.color.color14, "Color 14"));
        child2.add(new DemoChild(R.color.color15, "Color 15"));

        List<DemoChild> child3 = new ArrayList<>();
        child3.add(new DemoChild(R.color.color16, "Color 16"));
        child3.add(new DemoChild(R.color.color17, "Color 17"));
        child3.add(new DemoChild(R.color.color18, "Color 18"));
        child3.add(new DemoChild(R.color.color19, "Color 19"));

        List<DemoChild> child4 = new ArrayList<>();
        child4.add(new DemoChild(R.color.color20, "Color 20"));
        child4.add(new DemoChild(R.color.color21, "Color 21"));

        List<DemoParent> parent = new ArrayList<>();
        parent.add(new DemoParent("Color group 1", child));
        parent.add(new DemoParent("Color group 2", child1));
        parent.add(new DemoParent("Color group 3", child2));
        parent.add(new DemoParent("Color group 4", child3));
        parent.add(new DemoParent("Color group 5", child4));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcView.setLayoutManager(layoutManager);
        adapterParent = new AdapterParent(this, parent);
        rcView.setAdapter(adapterParent);
    }
}