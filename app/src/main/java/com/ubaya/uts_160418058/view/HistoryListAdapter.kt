package com.ubaya.uts_160418058.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.uts_160418058.R
import com.ubaya.uts_160418058.databinding.DailyListItemBinding
import com.ubaya.uts_160418058.databinding.HistoryListItemBinding
import com.ubaya.uts_160418058.model.Todo
//import com.ubaya.uts_160418058.model.history
import com.ubaya.uts_160418058.util.loadImage
import kotlinx.android.synthetic.main.daily_list_item.view.*
import kotlinx.android.synthetic.main.fragment_history.view.*
import kotlinx.android.synthetic.main.history_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HistoryListAdapter(val HistoryList:ArrayList<Todo>): RecyclerView.Adapter<HistoryListAdapter.HistoryListViewHolder>(),TodoDetailClick  {
    class HistoryListViewHolder(var view: HistoryListItemBinding) : RecyclerView.ViewHolder(view.root)
    fun updateHistoryList(newHistoryList: List<Todo>) {
        HistoryList.clear()
        HistoryList.addAll(newHistoryList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<HistoryListItemBinding>(inflater, R.layout.history_list_item, parent, false)
        return HistoryListViewHolder(v)
    }

    override fun onBindViewHolder(holder: HistoryListViewHolder, position: Int) {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val netDate = Date(HistoryList[position].time.toLong() * 1000)
        var time =  formatter.format(netDate);
        holder.view.todo = HistoryList[position]
        holder.view.txtDateHistory.text = time
        holder.view.detailListener = this
//        holder.view.txtTitleHistory.text = HistoryList[position].title
//        holder.view.txtCategoryHistory.text = HistoryList[position].category
//        holder.view.txtDateHistory.text = HistoryList[position].time
//        holder.view.txtPriorityhistory.text = HistoryList[position].priority
//        holder.view.imgHistory.loadImage(HistoryList[position].photo_url.toString(), holder.view.progressBarhistList)
    }

    override fun getItemCount(): Int {
        return HistoryList.size
    }

    override fun onTodoDetailClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = HistoryFragmentDirections.actionItemHistoryToDetailTaskFragment(uuid)

        Navigation.findNavController(v).navigate(action)
    }
}