package com.ubaya.uts_160418058.view

import android.view.View
import com.ubaya.uts_160418058.model.Todo

interface TodoDetailClick {
    fun onTodoDetailClick(v: View)
}

interface TodoEditClick {
    fun onTodoEditClick(v: View)
}
interface TodoDoneClick {
    fun onTodoDoneClick(v: View,id: Int)
}

interface TodoDeleteClick {
    fun onTodoDeleteClick(v: View,obj: Todo)
}

interface ButtonAddTodoClickListener {
    fun onButtonAddTodo(v: View)
}
interface TodoSaveChangesClick {
    fun onTodoSaveChangesClick(v: View, obj: Todo)
}

interface RadioClick {
    fun onRadioClick(v:View, tipe:String?, obj:Todo?)
}
interface DateClickListener {
    fun onDateClick(v: View)
}

interface TimeClickListener {
    fun onTimeClick(v: View)
}