package com.ubaya.uts_160418058.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.uts_160418058.R
import com.ubaya.uts_160418058.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.daily_list_item.*
import kotlinx.android.synthetic.main.fragment_detail_task.*
import java.util.concurrent.TimeUnit


class DetailTaskFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        var tipe = DetailTaskFragmentArgs.fromBundle(requireArguments()).tipe
        var id = DetailTaskFragmentArgs.fromBundle(requireArguments()).position

        if(tipe == "daily")
        {
            viewModel.fetchDaily(id)
            observeViewDaily()
        }else if(tipe == "weekly")
        {
            viewModel.fetchWeekly(id)
            observeViewWeekly()
        }
        else
        {
            viewModel.fetchMonthly(id)
            observeViewMonthly()
        }

    }
    fun observeViewDaily() {
        viewModel.dailyLD.observe(viewLifecycleOwner, Observer {
            txtTItle.setText(it.title)
            txtDateDetail.setText(it.time)
            txtDesc.setText(it.description)
            txtPrior.setText(it.priority)
            txtCategorydetail.setText(it.category)

        })
    }
    fun observeViewWeekly() {
        viewModel.weeklyLD.observe(viewLifecycleOwner, Observer {
            txtTItle.setText(it.title)
            txtDateDetail.setText(it.date)
            txtDesc.setText(it.description)
            txtPrior.setText(it.priority)
            txtCategorydetail.setText(it.category)

        })
    }
    fun observeViewMonthly() {
        viewModel.monthlyLD.observe(viewLifecycleOwner, Observer {
            txtTItle.setText(it.title)
            txtDateDetail.setText(it.month)
            txtDesc.setText(it.description)
            txtPrior.setText(it.priority)
            txtCategorydetail.setText(it.category)

        })
    }
}