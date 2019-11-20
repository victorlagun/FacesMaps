package com.example.victor.facesmaps.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.example.victor.facesmaps.UsersDataSource
import com.example.victor.facesmaps.adapter.UsersAdapter
import com.example.victor.facesmaps.viewmodel.UsersListViewModel
import kotlinx.android.synthetic.main.users_list_fragment.*
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
        val dataSource = UsersDataSource(this)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        val pagedList = PagedList.Builder(dataSource, config).build()
        adapter = UsersAdapter()
        adapter.submitList(pagedList)
        recyclerView.adapter = adapter
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
