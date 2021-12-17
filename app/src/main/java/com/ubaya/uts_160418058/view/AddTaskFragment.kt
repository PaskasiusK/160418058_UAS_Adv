package com.ubaya.uts_160418058.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import cn.pedant.SweetAlert.SweetAlertDialog
import com.ubaya.uts_160418058.R
import com.ubaya.uts_160418058.databinding.FragmentAddTaskBinding
import com.ubaya.uts_160418058.model.Todo
import com.ubaya.uts_160418058.util.Worker
import com.ubaya.uts_160418058.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.fragment_add_task.view.*
import java.util.*
import java.util.concurrent.TimeUnit


class AddTaskFragment : Fragment() ,ButtonAddTodoClickListener , RadioClick,DateClickListener,TimeClickListener,DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{
    private lateinit var viewModel: ListViewModel
    private lateinit var  dataBinding:FragmentAddTaskBinding
//    lateinit var radioButton: RadioButton
    var year = 0
    var month = 0
    var day = 0
    var hour = 0
    var minute = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        dataBinding.todo = Todo("","",0,"","","","",0)
        dataBinding.listener = this
        dataBinding.radioListener = this
        dataBinding.listenerDate = this
        dataBinding.listenerTime = this
    }



    override fun onRadioClick(v: View, tipe: String?, obj: Todo?) {
        obj?.tipe = tipe
    }

    override fun onButtonAddTodo(v: View) {
        val txt = dataBinding.todo!!.title
        val c = Calendar.getInstance()
        c.set(year,month,day,hour,minute,0)
        val today = Calendar.getInstance()
        val diff = (c.timeInMillis/1000L) - (today.timeInMillis/1000L)
       dataBinding.todo!!.time=(c.timeInMillis/1000L).toInt()
        viewModel.addTodo(dataBinding.todo!!)
        Toast.makeText(v.context,"Todo Created",Toast.LENGTH_SHORT).show()

        SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("Success!")
            .setContentText("To do Created Successfully!")
            .show()

        val myWorkReguest = OneTimeWorkRequestBuilder<Worker>()
            .setInitialDelay(diff, TimeUnit.SECONDS)
            .setInputData(workDataOf("TITLE" to txt,
                "MESSAGE" to "You have something to do!"))
            .build()
        WorkManager.getInstance(requireContext()).enqueue(myWorkReguest)
        Navigation.findNavController(v).popBackStack()

//       var todoAdd = Todo(obj.title.toString(), obj.description.toString(), obj.time.toString(),obj.category.toString(),obj.priority.toString(),obj.photo_url.toString(),obj.tipe.toString(),0)
//        viewModel.addTodo(dataBinding.todo!!)
//        viewModel.addTodo(todoAdd)
//        Toast.makeText(v.context, "Todo Added", Toast.LENGTH_SHORT).show()
    }

    override fun onDateClick(v: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        activity?.let{
                it -> DatePickerDialog(it,this,year,month,day).show()
        }
    }

    override fun onTimeClick(v: View) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        TimePickerDialog(activity,this,hour, minute, DateFormat.is24HourFormat(activity)).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Calendar.getInstance().let {
            it.set(year,month,dayOfMonth)
            dataBinding.root.txtDate.setText(
                dayOfMonth.toString().padStart(2,'0')+ "-"+
                        (month+1).toString().padStart(2,'0')+ "-" +
                        year.toString().padStart(2,'0'))
            this.year = year
            this.month = month
            this.day = dayOfMonth
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        dataBinding.root.txtTime.setText(
            hourOfDay.toString().padStart(2,'0')+ ":"+
                    minute.toString().padStart(2,'0')
        )
        hour=hourOfDay
        this.minute = minute
    }
}