package com.example.test2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.test2.MainActivity
import com.example.test2.R
import com.example.test2.demo.UserData
import com.example.test2.fragmentsingle.UserDetailFragment
import kotlinx.coroutines.*

class UserDataAdapter(private val mList: List<UserData>, private val context: Context) : RecyclerView.Adapter<UserDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_chat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = mList[position]

        val img = listOf(R.drawable.housekeeper)

        holder.imgProfile.setImageResource(img.get(userData.imgProfile))
        holder.tvUserName.text = userData.userName
        holder.tvLastMsg.text = userData.userLastChat
        holder.tvTime.text = userData.userTime

        if (userData.msgCount != 0) {
            holder.tvCount.text = userData.msgCount.toString()
        } else {
            holder.cvCount.visibility = View.INVISIBLE
        }

        holder.consPosition.setOnClickListener {
            val itemDetailFragment = UserDetailFragment.newInstance(UserDataAdapter(mList, context), position, mList.size)
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, itemDetailFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProfile: ImageView = itemView.findViewById(R.id.imgProfile)
        val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
        val tvLastMsg: TextView = itemView.findViewById(R.id.tvLatMsg)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val tvCount: TextView = itemView.findViewById(R.id.tvCount)
        val cvCount: CardView = itemView.findViewById(R.id.cvCount)
        val consPosition: ConstraintLayout = itemView.findViewById(R.id.consPosition)
    }

    fun getData(position: Int): UserData {
        return mList[position]
    }
}