package com.example.test2.fragmentsingle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.test2.R
import com.example.test2.adapter.UserDataAdapter
import com.example.test2.demo.UserData

class UserDetailFragment : Fragment() {

    private lateinit var userAdapter: UserDataAdapter
    private lateinit var userData: UserData
    private lateinit var imgProfile: ImageView
    private lateinit var tvUserName: TextView
    private lateinit var tvLastMsg: TextView
    private lateinit var tvTime: TextView
    private lateinit var cvPrevious: CardView
    private lateinit var cvNext: CardView
    private lateinit var imgMore: ImageButton
    private lateinit var imgBack: ImageButton
    private var position: Int = -1
    private var max: Int = -1

    companion object {
        fun newInstance(userAdapter: UserDataAdapter, p: Int, mList: Int): UserDetailFragment {
            val fragment = UserDetailFragment()
            fragment.userAdapter = userAdapter
            fragment.position = p
            fragment.max = mList
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_user_detail, container, false)

        // Initialize views
        imgProfile = view.findViewById(R.id.imgProfile)
        tvUserName = view.findViewById(R.id.tvUserName)
        tvLastMsg = view.findViewById(R.id.tvLastMsg)
        tvTime = view.findViewById(R.id.tvTime)
        cvPrevious = view.findViewById(R.id.cvPrevious)
        cvNext = view.findViewById(R.id.cvNext)
        imgMore = view.findViewById(R.id.btnMore)
        imgBack = view.findViewById(R.id.btnBack)

        // Retrieve UserData from UserDataAdapter
        userData = userAdapter.getData(position)

        // Set data to views
        tvUserName.text = userData.userName
        tvLastMsg.text = userData.userLastChat
        tvTime.text = userData.userTime

        if(position == 0){
            previousClick()
        }

        if (position == max-1){
            nextClick()
        }

        cvNext.setOnClickListener {
            // Retrieve UserData for next position and update views
            nextClick()
        }

        cvPrevious.setOnClickListener {
            // Retrieve UserData for previous position and update views
            previousClick()
        }

        imgBack.setOnClickListener{
            requireActivity().onBackPressed()
        }

        return view
    }

    private fun updateViews() {
        tvUserName.text = userData.userName
        tvLastMsg.text = userData.userLastChat
        tvTime.text = userData.userTime
    }

    private fun nextClick(){
        position++
        if (position >= max-1){
            position = max - 1
            cvNext.isEnabled = false
            cvNext.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.text_color)) // Use ContextCompat.getColor() to retrieve the color resource
        } else {
            cvNext.isEnabled = true
            cvNext.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange_light)) // Use ContextCompat.getColor() to retrieve the color resource
        }
        cvPrevious.isEnabled = true
        cvPrevious.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange_light)) // Use ContextCompat.getColor() to retrieve the color resource
        userData = userAdapter.getData(position)
        updateViews()
    }

    private fun previousClick(){
        position--
        if (position <= 0){
            position = 0
            cvPrevious.isEnabled = false
            cvPrevious.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.text_color)) // Use ContextCompat.getColor() to retrieve the color resource
        } else {
            cvPrevious.isEnabled = true
            cvPrevious.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange_light)) // Use ContextCompat.getColor() to retrieve the color resource
        }
        cvNext.isEnabled = true
        cvNext.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange_light)) // Use ContextCompat.getColor() to retrieve the color resource
        userData = userAdapter.getData(position)
        updateViews()
    }
}