package com.ubaya.uts_160418058.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import cn.pedant.SweetAlert.SweetAlertDialog
import com.ubaya.uts_160418058.R
import com.ubaya.uts_160418058.databinding.FragmentDetailTaskBinding
import com.ubaya.uts_160418058.model.Todo
import com.ubaya.uts_160418058.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.daily_list_item.*
import kotlinx.android.synthetic.main.fragment_detail_task.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class DetailTaskFragment : Fragment(),TodoDeleteClick,TodoDoneClick,TodoEditClick {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding:FragmentDetailTaskBinding
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentDetailTaskBinding>(inflater, R.layout.fragment_detail_task, container, false)
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
//        var tipe = DetailTaskFragmentArgs.fromBundle(requireArguments()).tipe
        var id = DetailTaskFragmentArgs.fromBundle(requireArguments()).position
        dataBinding.deleteListener = this
        dataBinding.editListener = this
        dataBinding.doneListener = this
        viewModel.fetch(id)
        observeViewModel()

//        if(tipe == "daily")
//        {
//            viewModel.fetchDaily(id)
//            observeViewDaily()
//        }else if(tipe == "weekly")
//        {
//            viewModel.fetchWeekly(id)
//            observeViewWeekly()
//        }
//        else
//        {
//            viewModel.fetchMonthly(id)
//            observeViewMonthly()
//        }

    }
    fun observeViewModel() {
        viewModel.TodoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
            val netDate = Date(dataBinding.todo!!.time.toLong() * 1000)
            var time =  formatter.format(netDate);

            txtDateDetail.setText(time)

        })
    }



    override fun onTodoDoneClick(v: View, id: Int) {
        viewModel.done(id)
        Toast.makeText(v.context, "Todo Done", Toast.LENGTH_SHORT).show()
        SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("Success!")
            .setContentText("To do Done!")
            .show()
        Navigation.findNavController(v).popBackStack()
    }

    override fun onTodoDeleteClick(v: View, obj: Todo) {
        viewModel.delete(obj)
        SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("Success!")
            .setContentText("To do Deleted Successfully!")
            .show()
        Navigation.findNavController(v).popBackStack()
    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = DetailTaskFragmentDirections.actionDetailTaskFragmentToEditTaskFragment(uuid)

        Navigation.findNavController(v).navigate(action)
    }
//    fun observeViewDaily() {
//        viewModel.dailyLD.observe(viewLifecycleOwner, Observer {
//            txtTItle.setText(it.title)
//            txtDateDetail.setText(it.time)
//            txtDesc.setText(it.description)
//            txtPrior.setText(it.priority)
//            txtCategorydetail.setText(it.category)
//
//        })
//    }
//    fun observeViewWeekly() {
//        viewModel.weeklyLD.observe(viewLifecycleOwner, Observer {
//            txtTItle.setText(it.title)
//            txtDateDetail.setText(it.date)
//            txtDesc.setText(it.description)
//            txtPrior.setText(it.priority)
//            txtCategorydetail.setText(it.category)
//
//        })
//    }
//    fun observeViewMonthly() {
//        viewModel.monthlyLD.observe(viewLifecycleOwner, Observer {
//            txtTItle.setText(it.title)
//            txtDateDetail.setText(it.month)
//            txtDesc.setText(it.description)
//            txtPrior.setText(it.priority)
//            txtCategorydetail.setText(it.category)
//
//        })
//    }
}