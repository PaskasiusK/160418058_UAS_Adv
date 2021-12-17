package com.ubaya.uts_160418058.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import cn.pedant.SweetAlert.SweetAlertDialog
import com.ubaya.uts_160418058.R
import com.ubaya.uts_160418058.databinding.FragmentEditTaskBinding
import com.ubaya.uts_160418058.model.Todo
import com.ubaya.uts_160418058.viewmodel.DetailViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [EditTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditTaskFragment : Fragment(),TodoSaveChangesClick,RadioClick{
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding:FragmentEditTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditTaskBinding>(inflater, R.layout.fragment_edit_task, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val uuid = EditTaskFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        dataBinding.listener = this
        dataBinding.radioListener = this
        observeViewModel()
    }fun observeViewModel() {
        viewModel.TodoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it

        })


    }
    override fun onRadioClick(v: View, tipe: String?, obj: Todo?) {
        obj?.tipe = tipe
    }

    override fun onTodoSaveChangesClick(v: View, obj: Todo) {
        val uuid = EditTaskFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.update(obj.title.toString(), obj.description.toString(), uuid,obj.category.toString(),obj.priority.toString(),obj.photo_url.toString(),obj.tipe.toString())
        Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
        SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("Success!")
            .setContentText("To do Updated Successfully!")
            .show()
        Navigation.findNavController(v).popBackStack()
    }



}