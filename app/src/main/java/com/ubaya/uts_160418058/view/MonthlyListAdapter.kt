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
//import com.ubaya.uts_160418058.model.monthly
import com.ubaya.uts_160418058.util.loadImage
import kotlinx.android.synthetic.main.daily_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MonthlyListAdapter(val MonthlyList:ArrayList<Todo>): RecyclerView.Adapter<MonthlyListAdapter.monthlyListViewHolder>(),TodoDetailClick
{
    class monthlyListViewHolder(var view: DailyListItemBinding) : RecyclerView.ViewHolder(view.root)
    fun updateMonthlyList(newMonthlyList: List<Todo>) {
        MonthlyList.clear()
        MonthlyList.addAll(newMonthlyList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): monthlyListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<DailyListItemBinding>(inflater, R.layout.daily_list_item, parent, false)
        return MonthlyListAdapter.monthlyListViewHolder(v)
    }

    override fun onBindViewHolder(holder: monthlyListViewHolder, position: Int) {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val netDate = Date(MonthlyList[position].time.toLong() * 1000)
        var time =  formatter.format(netDate);
        holder.view.todo = MonthlyList[position]
        holder.view.txtDate.text = time
        holder.view.detailListener = this
//        holder.view.txtTitle.text = MonthlyList[position].title
//        holder.view.txtCategory.text = MonthlyList[position].category
//        holder.view.txtDate.text = MonthlyList[position].time
//        holder.view.txtPriority.text = MonthlyList[position].priority
//        holder.view.imgProfile.loadImage(MonthlyList[position].photo_url.toString(), holder.view.progressBar)
//        holder.view.btnDetail.setOnClickListener{
//            val action = MonthlyListFragmentDirections.actionMovieDetail(position)
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return MonthlyList.size
    }

    override fun onTodoDetailClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = MonthlyListFragmentDirections.actionMonthlyDetail(uuid)

        Navigation.findNavController(v).navigate(action)
    }
}