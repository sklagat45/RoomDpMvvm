package com.srklagat.roomdbtest.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.srklagat.roomdbtest.R
import com.srklagat.roomdbtest.data.User
import kotlinx.android.synthetic.main.row_name_layout.view.*

class ListAdapter :RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_name_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = userList[position]
        holder.itemView.id_tv.text = currentItem.id.toString()
        holder.itemView.name_tv.text = currentItem.userName
        holder.itemView.email_tv.text = currentItem.userEmail
        holder.itemView.age_tv.text = currentItem.age.toString()

    }

    override fun getItemCount(): Int {

        return userList.size
    }

    fun setData(user: List<User>) {
        this.userList= user
        notifyDataSetChanged()
    }
}