package com.example.test2.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test2.R
import com.example.test2.adapter.UserDataAdapter
import com.example.test2.demo.UserData

class ChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_chat, container, false)
        val recyclerview = v.findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        // ArrayList of class ItemsViewModel
        val data = ArrayList<UserData>()

        data.add(UserData(0, "Darsh Dobariya", "Will meet soon .. .. .. ", "9:56 AM", 0))
        data.add(UserData(0, "Rajvi Navadiya", "Will meet soon .. .. .. ", "9:56 AM", 2))
        data.add(UserData(0, "Glenn Maxwell", "Will meet soon .. .. .. ", "9:56 AM", 7))
        data.add(UserData(0, "Vijay Thalapati", "Will meet soon .. .. .. ", "9:56 AM", 5))
        data.add(UserData(0, "Khushi Bodra", "Will meet soon .. .. .. ", "9:56 AM", 0))
        data.add(UserData(0, "Trusha Nandola", "Will meet soon .. .. .. ", "9:56 AM", 6))
        data.add(UserData(0, "Vishv Davra", "Will meet soon .. .. .. ", "9:56 AM", 1))
        data.add(UserData(0, "Reni Vaghasiya", "Will meet soon .. .. .. ", "9:56 AM", 0))
        data.add(UserData(0, "Vansh Kevadiya", "Will meet soon .. .. .. ", "9:56 AM", 0))
        data.add(UserData(0, "Ayush Navadiya", "Will meet soon .. .. .. ", "9:56 AM", 8))
        data.add(UserData(0, "Raj Bhadani", "Will meet soon .. .. .. ", "9:56 AM", 23))
        data.add(UserData(0, "Krisha Maxwell", "Will meet soon .. .. .. ", "9:56 AM", 5))
        data.add(UserData(0, "Krina Thalapati", "Will meet soon .. .. .. ", "9:56 AM", 0))
        data.add(UserData(0, "Rajiv Gandhi", "Will meet soon .. .. .. ", "9:56 AM", 3))
        data.add(UserData(0, "Narendra Modi", "Will meet soon .. .. .. ", "9:56 AM", 5))
        data.add(UserData(0, "Geeta Vasoya", "Will meet soon .. .. .. ", "9:56 AM", 3))
        data.add(UserData(0, "Shrenil Gopani", "Will meet soon .. .. .. ", "9:56 AM", 0))
        data.add(UserData(0, "Vishva Kakadiya", "Will meet soon .. .. .. ", "9:56 AM", 0))

        // This will pass the ArrayList to our Adapter
        val adapter = UserDataAdapter(data, requireContext())

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
        return v
    }
}