package com.example.victor.facesmaps.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.victor.facesmaps.R
import com.example.victor.facesmaps.model.User
import com.example.victor.facesmaps.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.user_fragment.*

class UserFragment : Fragment() {

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        arguments?.let {
            viewModel.getUser(it.getInt("id"))
                .observe(this, Observer { user -> user?.let { fill(user) } })
        }
    }

    private fun fill(user: User) {
        Glide
            .with(this)
            .load(user.avatar)
            .into(avatar)
        firstName.text = user.first_name
        lastName.text = user.last_name
        email.text = user.email
    }

}
