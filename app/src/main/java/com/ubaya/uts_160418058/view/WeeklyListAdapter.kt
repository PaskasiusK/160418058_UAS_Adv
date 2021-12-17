package com.ubaya.uts_160418058.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.uts_160418058.R
import com.ubaya.uts_160418058.databinding.DailyListItemBinding
import com.ubaya.uts_160418058.model.Todo
//import com.ubaya.uts_160418058.model.daily
//import com.ubaya.uts_160418058.model.weekly
import com.ubaya.uts_160418058.util.loadImage
import kotlinx.android.synthetic.main.daily_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WeeklyListAdapter(val WeeklyList:ArrayList<Todo>): RecyclerView.Adapter<WeeklyListAdapter.weeklyListViewHolder>() ,TodoDetailClick{
    class weeklyListViewHolder(var view: DailyListItemBinding) : RecyclerView.ViewHolder(view.root)
    fun updateWeeklyList(newWeeklyList: List<Todo>) {
        WeeklyList.clear()
        WeeklyList.addAll(newWeeklyList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): weeklyListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<DailyListItemBinding>(inflater, R.layout.daily_list_item, parent, false)
        return WeeklyListAdapter.weeklyListViewHolder(v)
    }

    override fun onBindViewHolder(holder: weeklyListViewHolder, position: Int) {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val netDate = Date(WeeklyList[position].time.toLong() * 1000)
        var time =  formatter.format(netDate);
        holder.view.txtDate.text = time
        holder.view.todo = WeeklyList[position]
        holder.view.detailListener = this
//        holder.view.txtTitle.text = WeeklyList[position].title
//        holder.view.txtCategory.text = WeeklyList[position].category
//        holder.view.txtDate.text = WeeklyList[position].time
//        holder.view.txtPriority.text = WeeklyList[position].priority
//        holder.view.imgProfile.loadImage(WeeklyList[position].photo_url.toString(), holder.view.progressBar)
//        holder.view.btnDetail.setOnClickListener{
//            val action = WeeklyListFragmentDirections.actionWeeklyDetail(position)
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    override fun getItemCount(): Int {

        return WeeklyList.size
    }

    override fun onTodoDetailClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = WeeklyListFragmentDirections.actionWeeklyDetail(uuid)

        Navigation.findNavController(v).navigate(action)
    }
}