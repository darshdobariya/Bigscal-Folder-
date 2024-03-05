package com.example.androidtest.homefragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidtest.MainActivity2;
import com.example.androidtest.R;
import com.example.androidtest.adapter.UserAdapter;
import com.example.androidtest.demo.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class EventFragment extends Fragment {
    RecyclerView rcEvent;
    UserAdapter adapter;
    ArrayList<User> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_event, container, false);

        rcEvent = (RecyclerView) view.findViewById(R.id.rcEvent);

        list = new ArrayList<>();
        list.add(new User("Darsh Dobariya", 5, "Male", "Information Technology and Science", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Rajvi Navadiya", 10, "Female", "Computer Science", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Glenn Maxwell", 15, "Male", "Chemical Engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Vijay Thalapati", 12, "Male", "Data Scientist", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Khushi Bodra", 52, "Female", "Mechanical engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Trusha Nandola", 26, "Female", "Information Technology", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Vishv Davra", 15, "Male", "Civil engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Reni Vaghasiya", 86, "Female", "Chemical Engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Vansh Kevadiya", 1, "Male", "BlockChain Developer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));

        list.add(new User("Ayush Navadiya", 7, "Male", "Information Technology", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Raj Bhadani", 25, "Male", "Computer Science", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Krisha Maxwell", 62, "Female", "Chemical Engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Krina Thalapati", 24, "Female", "Data Scientist", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Rajiv Gandhi", 14, "Male", "Mechanical engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Narendra Modi", 11, "Male", "Information Technology", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Geeta Vasoya", 36, "Female", "Civil engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Shrenil Gopani", 35, "Male", "Chemical Engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Vishva Kakadiya", 14, "Female", "BlockChain Developer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));

        list.add(new User("Darsh Dobariya", 5, "Male", "Information Technology", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Rajvi Navadiya", 10, "Female", "Computer Science", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Glenn Maxwell", 15, "Male", "Chemical Engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Vijay Thalapati", 12, "Male", "Data Scientist", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Khushi Bodra", 52, "Female", "Mechanical engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Trusha Nandola", 26, "Female", "Information Technology", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Vishv Davra", 15, "Male", "Civil engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Reni Vaghasiya", 86, "Female", "Chemical Engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Vansh Kevadiya", 1, "Male", "BlockChain Developer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));

        list.add(new User("Ayush Navadiya", 7, "Male", "Information Technology", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Raj Bhadani", 25, "Male", "Computer Science", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Krisha Maxwell", 62, "Female", "Chemical Engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Krina Thalapati", 24, "Female", "Data Scientist", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Rajiv Gandhi", 14, "Male", "Mechanical engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Narendra Modi", 11, "Male", "Information Technology", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Geeta Vasoya", 36, "Female", "Civil engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Shrenil Gopani", 35, "Male", "Chemical Engineer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));
        list.add(new User("Vishva Kakadiya", 14, "Female", "BlockChain Developer", "1st floor, millennium point lal darwaja, katargam, surat- 395004. "));

        adapter = new UserAdapter((MainActivity2) getActivity(), list);
        // at last set adapter to recycler view.
        rcEvent.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rcEvent.setAdapter(adapter);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration((MainActivity2) requireActivity(), DividerItemDecoration.HORIZONTAL);
//        rcEvent.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rcEvent);

        return view;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            Collections.swap(list, fromPosition, toPosition);
            Objects.requireNonNull(recyclerView.getAdapter()).notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };
}