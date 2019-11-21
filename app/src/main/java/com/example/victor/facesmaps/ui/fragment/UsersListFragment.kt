package com.example.victor.facesmaps.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.victor.facesmaps.R
import com.example.victor.facesmaps.viewmodel.UsersListViewModel
import kotlinx.android.synthetic.main.users_list_fragment.*

class UsersListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewModel: UsersListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.users_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UsersListViewModel::class.java)
        swiperefreshlayout.setOnRefreshListener(this)
        recyclerView.adapter = viewModel.getAdapter()
    }

    override fun onRefresh() {
        viewModel.getDataSource().invalidate()
        recyclerView.adapter?.notifyDataSetChanged()
        swiperefreshlayout.isRefreshing = false
    }

}
