package com.example.victor.facesmaps.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.arch.core.executor.ArchTaskExecutor.getMainThreadExecutor
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.example.victor.facesmaps.R
import com.example.victor.facesmaps.model.User
import com.example.victor.facesmaps.paging.UsersAdapter
import com.example.victor.facesmaps.paging.UsersDataSource
import com.example.victor.facesmaps.viewmodel.UsersListViewModel
import kotlinx.android.synthetic.main.users_list_fragment.view.*
import java.util.concurrent.Executor


class UsersListFragment : Fragment() {

    lateinit var adapter: UsersAdapter

    companion object {
        fun newInstance() = UsersListFragment()
    }

    private lateinit var viewModel: UsersListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.users_list_fragment, container, false)
        val dataSource = UsersDataSource()
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        val pagedList = PagedList.Builder<Int, User>(dataSource, config).setFetchExecutor(
            getMainThreadExecutor())
            .setNotifyExecutor(getMainThreadExecutor()).build()
        adapter = UsersAdapter()
        adapter.submitList(pagedList)
        view.recyclerView.adapter = adapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UsersListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

internal class MainThreadExecutor : Executor {
    private val mHandler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        mHandler.post(command)
    }
}
