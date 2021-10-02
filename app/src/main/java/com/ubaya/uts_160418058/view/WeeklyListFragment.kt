package com.ubaya.uts_160418058.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.uts_160418058.R
import com.ubaya.uts_160418058.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_daily_list.*
import kotlinx.android.synthetic.main.fragment_daily_list.refreshLayout
import kotlinx.android.synthetic.main.fragment_weekly_list.*


class WeeklyListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val WeeklyListAdapter = WeeklyListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weekly_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refreshWeekly()
        recViewWeek.layoutManager = LinearLayoutManager(context)
        recViewWeek.adapter = WeeklyListAdapter
        refreshLayoutWeekly.setOnRefreshListener {
            recViewWeek.visibility = View.GONE
            txtErrorWeek.visibility = View.GONE
            progressBar2.visibility = View.VISIBLE
            viewModel.refreshWeekly()
            refreshLayoutWeekly.isRefreshing = false
        }

        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.weeklyLD.observe(viewLifecycleOwner, Observer {
            WeeklyListAdapter.updateWeeklyList(it)

        })
        viewModel.taskLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                txtErrorWeek.visibility = View.VISIBLE
            } else {
                txtErrorWeek.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recViewWeek.visibility = View.GONE
                progressBar2.visibility = View.VISIBLE
            } else {
                recViewWeek.visibility = View.VISIBLE
                progressBar2.visibility = View.GONE
            }
        })


    }
}