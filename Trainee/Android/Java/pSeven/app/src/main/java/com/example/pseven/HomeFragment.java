package com.example.pseven;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ItemAdapter.OnItemClickListener{

    private List<Recipe> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView rcItem = v.findViewById(R.id.rcItem);

        list = new ArrayList<>();
        list.add(new Recipe(R.drawable.one, "Item One", "Pizza is a savory dish of Italian origin consisting of a usually round, flattened base of leavened wheat-based dough topped with tomatoes, cheese, and often various other ingredients."));
        list.add(new Recipe(R.drawable.two_, "Item Two", "Burger is a sandwich consisting of one or more cooked patties of ground meat, usually beef, placed inside a sliced bread roll or bun."));
        list.add(new Recipe(R.drawable.three, "Item Three", "Samosa is a fried or baked pastry with a savory filling, such as spiced potatoes, onions, peas, meat, or lentils."));
        list.add(new Recipe(R.drawable.four, "Item Four", "Pasta is a type of Italian food typically made from an unleavened dough of wheat flour mixed with water or eggs, and formed into sheets or other shapes, then cooked by boiling or baking."));
        list.add(new Recipe(R.drawable.five, "Item Five", "Sushi is a Japanese dish consisting of small balls or rolls of vinegar-flavored cold cooked rice served with a garnish of raw fish, vegetables, or egg."));
        list.add(new Recipe(R.drawable.six, "Item Six", "Taco is a traditional Mexican dish consisting of a corn or wheat tortilla folded or rolled around a filling, typically of meat, beans, lettuce, and salsa."));
        list.add(new Recipe(R.drawable.seven, "Item Seven", "Curry is a variety of dishes originating in the Indian subcontinent that use a complex combination of spices or herbs, usually including ground turmeric, cumin, coriander, ginger, and fresh or dried chilies."));
        list.add(new Recipe(R.drawable.eight, "Item Eight", "Ramen is a Japanese noodle soup dish consisting of Chinese-style wheat noodles served in a meat- or fish-based broth, often flavored with soy sauce or miso, and topped with various ingredients such as sliced pork, nori, and green onions."));
        list.add(new Recipe(R.drawable.nine, "Item Nine", "Lasagna is a traditional Italian dish made with layers of flat pasta sheets, cheese, meat, and tomato sauce."));
        list.add(new Recipe(R.drawable.ten, "Item Ten", "Sushi is a Japanese dish consisting of small balls or rolls of vinegar-flavored cold cooked rice served with a garnish of raw fish, vegetables, or egg."));
        list.add(new Recipe(R.drawable.el, "Item Eleven", "Biryani is a mixed rice dish originating among the Muslims of the Indian subcontinent made with Indian spices, rice, and meat (chicken, beef, goat, pork, lamb, prawn, or fish), and sometimes, in addition, eggs and/or vegetables such as potatoes in certain regional varieties."));
        list.add(new Recipe(R.drawable.tw, "Item Twelve", "Pho is a Vietnamese soup consisting of broth, rice noodles, herbs, and meat, primarily made with either beef or chicken."));
        list.add(new Recipe(R.drawable.th, "Item Thirteen", "Tacos Al Pastor is a Mexican dish made with spit-grilled pork, similar to shawarma, usually served as a taco."));
        list.add(new Recipe(R.drawable.fo, "Item Fourteen", "Ratatouille is a French Provencal stewed vegetable dish, originating in Nice."));
        list.add(new Recipe(R.drawable.fi, "Item Fifteen", "Peking Duck is a dish from Beijing (Peking) that has been prepared since the Imperial era."));

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        rcItem.setLayoutManager(layoutManager);

        ItemAdapter adapter = new ItemAdapter(requireActivity(), list, this);
        rcItem.setAdapter(adapter);

        return v;
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click event here
        Recipe clickedRecipe = list.get(position);
        openChildFragment(clickedRecipe.getImgId(), clickedRecipe.getItem(), clickedRecipe.getDescription());
    }

    private void openChildFragment(int imgId, String name, String description) {
        // Create the child fragment and pass data to it
       /* Fragment childFragment = new ItemInfoFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("imageId", imgId);
        bundle.putString("itemName", name);
        bundle.putString("itemDescription", description);
        childFragment.setArguments(bundle);

        // Navigate to the child fragment
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frmItemClick, childFragment)
                .addToBackStack(null)
                .commit();*/

        getParentFragmentManager().beginTransaction()
                .replace(R.id.frmLayout, new ItemInfoFragment())
                .addToBackStack(null)
                .commit();
    }
}