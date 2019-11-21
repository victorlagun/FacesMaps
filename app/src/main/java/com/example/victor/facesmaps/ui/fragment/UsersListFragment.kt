package com.example.victor.facesmaps.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.victor.facesmaps.App
import com.example.victor.facesmaps.R
import com.example.victor.facesmaps.paging.UsersAdapter
import com.example.victor.facesmaps.repository.impl.RepositoryImpl
import com.example.victor.facesmaps.ui.activity.MainActivity
import com.example.victor.facesmaps.viewmodel.UsersListViewModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
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
        val adapter = UsersAdapter()
        viewModel.getPagedList().observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
    }

    @SuppressLint("CheckResult")
    override fun onRefresh() {
        if ((activity as MainActivity).isNetworkAvailable) {
            RepositoryImpl.setDefault()
            Observable.just(App.instance.database)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    it.userDao().delete()
                    swiperefreshlayout.isRefreshing = false
                }
        } else {
            swiperefreshlayout.isRefreshing = false
        }
    }

}
