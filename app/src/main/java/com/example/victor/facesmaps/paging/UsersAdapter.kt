package com.example.victor.facesmaps.paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.victor.facesmaps.R
import com.example.victor.facesmaps.model.User
import com.example.victor.facesmaps.ui.activity.MainActivity
import kotlinx.android.synthetic.main.item_users_list.view.*


class UsersAdapter :
    PagedListAdapter<User, RecyclerView.ViewHolder>(UserDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(getItem(position))
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

}

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
    init {itemView.setOnClickListener(this)}

    lateinit var user: User

    fun bind(user: User?) {
        user?.let {
            this.user = user
            Glide
                .with(itemView.context)
                .load(user.avatar)
                .into(itemView.imageView)
            itemView.firstName.text = user.first_name
            itemView.lastName.text = user.last_name
        }
    }

    override fun onClick(v: View?) {
        val bundle = Bundle()
        bundle.putInt("id", user.id)
        (v?.context as MainActivity).navController.navigate(R.id.userFragment, bundle)
    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_users_list, parent, false)
            return UserViewHolder(view)
        }
    }
}