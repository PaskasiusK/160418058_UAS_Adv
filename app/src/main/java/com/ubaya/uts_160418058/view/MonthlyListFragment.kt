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
import kotlinx.android.synthetic.main.fragment_monthly_list.*
import kotlinx.android.synthetic.main.fragment_weekly_list.*


class MonthlyListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val monthlyListAdapter = MonthlyListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monthly_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refreshMonthly()
        recViewMonthly.layoutManager = LinearLayoutManager(context)
        recViewMonthly.adapter = monthlyListAdapter
        refreshLayoutMonthly.setOnRefreshListener {
            recViewMonthly.visibility = View.GONE
            txtErrorMonthly.visibility = View.GONE
            progressBarMonthly.visibility = View.VISIBLE
            viewModel.refreshMonthly()
            refreshLayoutMonthly.isRefreshing = false
        }

        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.TodoLD.observe(viewLifecycleOwner, Observer {
            monthlyListAdapter.updateMonthlyList(it)

        })
        viewModel.taskLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                txtErrorMonthly.visibility = View.VISIBLE
            } else {
                txtErrorMonthly.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recViewMonthly.visibility = View.VISIBLE
                progressBarMonthly.visibility = View.GONE
            } else {

                recViewMonthly.visibility = View.GONE
                progressBarMonthly.visibility = View.VISIBLE
            }
        })


    }
}