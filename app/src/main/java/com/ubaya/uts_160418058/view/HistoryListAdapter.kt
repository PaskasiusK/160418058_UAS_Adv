package com.ubaya.uts_160418058.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.uts_160418058.R
import com.ubaya.uts_160418058.model.history
import com.ubaya.uts_160418058.util.loadImage
import kotlinx.android.synthetic.main.daily_list_item.view.*
import kotlinx.android.synthetic.main.fragment_history.view.*
import kotlinx.android.synthetic.main.history_list_item.view.*

class HistoryListAdapter(val HistoryList:ArrayList<history>): RecyclerView.Adapter<HistoryListAdapter.HistoryListViewHolder>()  {
    class HistoryListViewHolder(var view: View) : RecyclerView.ViewHolder(view)
    fun updateHistoryList(newHistoryList: List<history>) {
        HistoryList.clear()
        HistoryList.addAll(newHistoryList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.history_list_item, parent, false)
        return HistoryListAdapter.HistoryListViewHolder(v)
    }

    override fun onBindViewHolder(holder: HistoryListViewHolder, position: Int) {
        holder.view.txtTitleHistory.text = HistoryList[position].title
        holder.view.txtCategoryHistory.text = HistoryList[position].category
        holder.view.txtDateHistory.text = HistoryList[position].date
        holder.view.txtPriorityhistory.text = HistoryList[position].priority
        holder.view.imgHistory.loadImage(HistoryList[position].photo_url.toString(), holder.view.progressBarhistList)
    }

    override fun getItemCount(): Int {
        return HistoryList.size
    }
}