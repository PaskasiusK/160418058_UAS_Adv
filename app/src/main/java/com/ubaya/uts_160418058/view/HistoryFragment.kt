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
import kotlinx.android.synthetic.main.fragment_daily_list.refreshLayout
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val historyListAdapter = HistoryListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refreshHistory()
        recViewHistory.layoutManager = LinearLayoutManager(context)
        recViewHistory.adapter = historyListAdapter
        refreshLayoutHistory.setOnRefreshListener {
            recViewHistory.visibility = View.GONE
            txtErrorHistory.visibility = View.GONE
            progressBarHistory.visibility = View.VISIBLE
            viewModel.refreshHistory()
            refreshLayoutHistory.isRefreshing = false
        }

        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.historyLD.observe(viewLifecycleOwner, Observer {
            historyListAdapter.updateHistoryList(it)

        })
        viewModel.taskLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                txtErrorHistory.visibility = View.VISIBLE
            } else {
                txtErrorHistory.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recViewHistory.visibility = View.GONE
                progressBarHistory.visibility = View.VISIBLE
            } else {
                recViewHistory.visibility = View.VISIBLE
                progressBarHistory.visibility = View.GONE
            }
        })


    }
}